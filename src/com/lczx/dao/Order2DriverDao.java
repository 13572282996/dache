package com.lczx.dao;

import java.util.List;

import com.lczx.entity.Order2Driver;

public interface Order2DriverDao extends BaseDao<Order2Driver, Long>
{
    List<Order2Driver> queryOrder2DriversByMobile(String driverPhone);
    
    List<Order2Driver> queryOrder2DriversByOrderId(String orderId);
    
    //boolean 
}
