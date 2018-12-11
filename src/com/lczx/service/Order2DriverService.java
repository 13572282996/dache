package com.lczx.service;

import java.util.List;

import com.lczx.entity.Order;
import com.lczx.entity.Order2Driver;

public interface Order2DriverService extends BaseService<Order2Driver, Long>
{
    List<Order2Driver> queryOrder2DriversByMobile(String driverPhone);
    
    void addOrder2Driver(Order order) throws Exception;
    
    
}
