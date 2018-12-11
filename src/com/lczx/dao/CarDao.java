package com.lczx.dao;

import com.lczx.entity.Car;

public interface CarDao extends BaseDao<Car, Long>
{

    Car findCarByDriver(String driver);
    
}
