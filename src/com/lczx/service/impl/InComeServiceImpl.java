package com.lczx.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.InComeDao;
import com.lczx.entity.DriverInCome;
import com.lczx.entity.InCome;
import com.lczx.service.InComeService;

@Service("inComeServiceImpl")
public class InComeServiceImpl extends BaseServiceImpl<InCome, Long> implements
        InComeService
{
    @Resource(name = "inComeDaoImpl")
    private InComeDao inComeDao;
    
    @Resource(name = "inComeDaoImpl")
    public void setBaseDao(InComeDao inComeDao)
    {
        super.setBaseDao(inComeDao);
    }
    
    @Override
    public BigDecimal driverInComeSum(Long driverId, String time)
    {
        return inComeDao.driverInComeSum(driverId, time);
    }
    
    @Override
    public BigDecimal inComeSum(Long driverId, boolean settlement)
    {
        return inComeDao.inComeSum(driverId, settlement);
    }
    
    @Override
    public BigDecimal incomeSum(Long driverId, boolean settlement, String time)
    {
        return inComeDao.incomeSum(driverId, settlement, time);
    }
    
    @Override
    public List<DriverInCome> driverInCome(boolean settlement)
    {
        return inComeDao.driverInCome(settlement);
    }
    
    @Override
    public List<InCome> unSettlement(Long driverId)
    {
        return inComeDao.unSettlement(driverId);
    }
    
    @Override
    public void settlementing(Long driverId)
    {
        List<InCome> list = inComeDao.unSettlement(driverId);
        if (list != null && list.size() != 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                list.get(i).setSettlement(true);
                super.update(list.get(i));
            }
        }
        else {
            return;
        }
        
    }
    
}
