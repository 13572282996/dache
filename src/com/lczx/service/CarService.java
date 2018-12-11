package com.lczx.service;

import com.lczx.entity.Car;

public interface CarService extends BaseService<Car, Long>
{
    Car findCarByDriver(String driver);
}
