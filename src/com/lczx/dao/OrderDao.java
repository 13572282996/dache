package com.lczx.dao;

import com.lczx.entity.Order;


public interface OrderDao extends BaseDao<Order, Long>
{

    Order queryOrderByNum(String orderNum);
    /**
     * 司机当月接单数 
     **/
    Long orderCountByMonth(String driver);
    
    /**
     * 司机当日接单数 
     **/
    Long orderCountByToday(String driver);
    /**
     * 司机订单平均分 
     * 
     **/
    double driverScore(String driver);
    
}
