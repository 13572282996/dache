package com.lczx.service;

import java.math.BigDecimal;
import java.util.List;

import com.lczx.entity.DriverInCome;
import com.lczx.entity.InCome;

public interface InComeService extends BaseService<InCome, Long>
{
    /**
     * 司机总收入 
     * 
     **/
    BigDecimal driverInComeSum(Long driverId,String time);
    /**
     * 根据结算状态查询司机收入 
     * 
     **/
    BigDecimal inComeSum(Long driverId, boolean settlement);
    /**
     * 查询收入结算数据
     * driverId　司机ＩＤ
     * settlement　是否结算
     *  month：本月
     *  week：本周
     *  lastMonth：上月
     *  season：本季
     * 
     **/
    BigDecimal incomeSum(Long driverId, boolean settlement, String time);
    /**
     * 司机未结算数据统计 
     * 
     **/
    List<DriverInCome> driverInCome(boolean settlement);
    
    /**
     * 待结算列表 
     * 
     **/
    List<InCome> unSettlement(Long driverId);
    /**
     * 结算 
     * 
     **/
    void settlementing(Long driverId);
    
    
}
