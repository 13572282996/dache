package com.lczx.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lczx.dao.InComeDao;
import com.lczx.entity.Car;
import com.lczx.entity.DriverInCome;
import com.lczx.entity.InCome;

@Repository("inComeDaoImpl")
public class InComeDaoImpl extends BaseDaoImpl<InCome, Long> implements
        InComeDao
{
    
    @Override
    public BigDecimal driverInComeSum(Long driverId, String time)
    {
        String sql = "select sum(i.amount) from dc_income i where i.driver ="
                + driverId;
        if ("today".equals(time))
        {
            sql += "  AND TO_DAYS(create_date) = TO_DAYS(now())";
        }
        if ("month".equals(time))
        {
            sql += " AND DATE_FORMAT(create_date,'%Y%m') = DATE_FORMAT(CURDATE( ),'%Y%m')";
        }
        if ("week".equals(time))
        {
            sql += " AND YEARWEEK( date_format(  create_date,'%Y-%m-%d' ) ) = YEARWEEK( now() )";
        }
        if ("lastMonth".equals(time))
        {
            sql += " AND PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(create_date,'%Y%m') =1)";
        }
        if ("season".equals(time))
        {
            sql += " AND QUARTER(create_date)=QUARTER(now())";
        }
        Query query = entityManager.createNativeQuery(sql);
        BigDecimal sumBigDecimal = new BigDecimal("0");
        if (query.getSingleResult() != null)
        {
            sumBigDecimal = new BigDecimal(query.getSingleResult().toString());
        }
        return sumBigDecimal;
    }
    
    @Override
    public BigDecimal inComeSum(Long driverId, boolean settlement)
    {
        String sql = "select sum(i.amount) from dc_income i where i.driver ="
                + driverId + " and i.settlement = " + settlement;
        //   + " AND DATE_FORMAT(create_date,'%Y%m') = DATE_FORMAT(CURDATE( ),'%Y%m')";
        Query query = entityManager.createNativeQuery(sql);
        
        BigDecimal sumBigDecimal = new BigDecimal("0");
        if (query.getSingleResult() != null)
        {
            sumBigDecimal = new BigDecimal(query.getSingleResult().toString());
        }
        return sumBigDecimal;
    }
    
    @Override
    public BigDecimal incomeSum(Long driverId, boolean settlement, String time)
    {
        String sql = "select sum(i.amount) from dc_income i where 1=1 and i.settlement = "
                + settlement;
        if (driverId != null)
        {
            sql += " and i.driver =" + driverId;
        }
        if ("month".equals(time))
        {
            sql += " AND DATE_FORMAT(create_date,'%Y%m') = DATE_FORMAT(CURDATE( ),'%Y%m')";
        }
        if ("week".equals(time))
        {
            sql += " AND YEARWEEK( date_format(  create_date,'%Y-%m-%d' ) ) = YEARWEEK( now() )";
        }
        if ("lastMonth".equals(time))
        {
            sql += " AND PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(create_date,'%Y%m') =1)";
        }
        if ("season".equals(time))
        {
            sql += " AND QUARTER(create_date)=QUARTER(now())";
        }
        Query query = entityManager.createNativeQuery(sql);
        BigDecimal sumBigDecimal = new BigDecimal("0");
        if (query.getSingleResult() != null)
        {
            sumBigDecimal = new BigDecimal(query.getSingleResult().toString());
        }
        return sumBigDecimal;
    }
    
    @Override
    public List<DriverInCome> driverInCome(boolean settlement)
    {
        
        String sql = "select i.driver,d.driver_name,d.driver_phone ,d.id_card,d.vehicle_no,sum(i.amount) from dc_income i ,dc_driver d where i.settlement = " + settlement + " and i.driver = d.id group by i.driver";
        List<DriverInCome> driverInComes = new ArrayList<DriverInCome>();
        Query query = entityManager.createNativeQuery(sql);
        List list = query.getResultList();
        for (Object obj : list)
        {
            Object[] cells = (Object[]) obj;
            DriverInCome diCome = new DriverInCome();
            diCome.setId(Long.valueOf(cells[0].toString()));
            diCome.setName(cells[1].toString());
            diCome.setMobile(cells[2].toString());
            diCome.setIdCard(cells[3].toString());
            diCome.setVehicleNo(cells[4].toString());
            diCome.setAmount(new BigDecimal(cells[5].toString()));
            driverInComes.add(diCome);
        }
        return driverInComes;
    }
    
    @Override
    public List<InCome> unSettlement(Long driverId)
    {
        String jpql = "select inCome from InCome inCome where lower(inCome.driver) = lower(:driverId) and inCome.settlement = false";
        
        return entityManager.createQuery(jpql, InCome.class)
                .setFlushMode(FlushModeType.COMMIT)
                .setParameter("driverId", driverId)
                .getResultList();
        
    }
    
}
