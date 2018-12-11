package com.lczx.service;

import java.util.List;

import com.lczx.entity.Driver;
import com.lczx.entity.Order;

public interface OrderService extends BaseService<Order, Long>
{
    /**
     * 抢单 
     * 
     **/
     Order receiptOrder(Driver driver,String orderId);
     /**
      * 查询订单 
      * 根握订单编号
      **/
     Order queryOrderbyNum(String orderNum);
     
     /**
      * 根据司机手机号码查订单列表
      * 
      **/
     List<Order> queryOrderByDriver(String driver);
     /**
      * 统计当日司机接单数
      * 
      **/
     Long orderCountByToday(String driver);
     /**
      * 统计当月司机接单数
      * 
      **/
     
     Long orderCountByMonth(String driver);
     /**
      * 乘客户取消订单
      * 
      **/
     boolean cancelOrder(String orderId);
     
     double driverScore(String driver);
}
