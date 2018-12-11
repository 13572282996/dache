package com.lczx.dao;

import java.math.BigDecimal;
import java.util.List;

import com.lczx.entity.DriverInCome;
import com.lczx.entity.InCome;

/**
 * 司机收入 
 * 
 **/
public interface InComeDao extends BaseDao<InCome, Long>
{
    /**
     * 司机总收入 
     * 
     **/
    BigDecimal driverInComeSum(Long driverId, String time);
    
    /**
     * 司机结算收入
     *  
     **/
    BigDecimal inComeSum(Long driverId, boolean settlement);
    
    BigDecimal incomeSum(Long driverId, boolean settlement, String time);
    
    List<DriverInCome> driverInCome(boolean settlement);
    
    List<InCome> unSettlement(Long driverId);
    
}
