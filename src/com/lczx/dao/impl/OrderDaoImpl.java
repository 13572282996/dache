package com.lczx.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lczx.dao.OrderDao;
import com.lczx.entity.Order;

@Repository("orderDaoImpl")
public class OrderDaoImpl extends BaseDaoImpl<Order, Long> implements OrderDao
{
    
    @Override
    public Order queryOrderByNum(String orderNum)
    {
        if (orderNum == null)
        {
            return null;
        }
        try
        {
            String jpql = "select orders from Order orders where orders.num = (:orderNum)";
            return entityManager.createQuery(jpql, Order.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("orderNum", orderNum)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }
    
    @Override
    public Long orderCountByMonth(String driver)
    {
        String sql = "SELECT count(1) FROM dc_order o WHERE o.dirver_mobile = "
                + driver
                + " AND DATE_FORMAT(create_date,'%Y%m') = DATE_FORMAT(CURDATE( ),'%Y%m')";
        Query query = entityManager.createNativeQuery(sql);
        Long countLong = Long.valueOf(query.getSingleResult().toString());
        return countLong;
    }
    
    @Override
    public Long orderCountByToday(String driver)
    {
        String sql = "SELECT count(1) FROM dc_order o WHERE o.dirver_mobile = "
                + driver + " AND TO_DAYS(create_date) = TO_DAYS(now())";
        Query query = entityManager.createNativeQuery(sql);
        Long countLong = Long.valueOf(query.getSingleResult().toString());
        return countLong;
    }
    
    @Override
    public double driverScore(String driver)
    {
        String sql = "select AVG(o1.score) from dc_order o1 where o1.dirver_mobile ="
                + driver + " AND o1.order_status = 3";
        Query query = entityManager.createNativeQuery(sql);
        double score = 5;
        if (query.getSingleResult() != null)
        {
            score = Double.valueOf(query.getSingleResult().toString());
        }
        return score;
    }
    
}
