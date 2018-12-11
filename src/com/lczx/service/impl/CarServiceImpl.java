package com.lczx.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.CarDao;
import com.lczx.entity.Car;
import com.lczx.service.CarService;

@Service("carServiceImpl")
public class CarServiceImpl extends BaseServiceImpl<Car, Long> implements CarService
{
    @Resource(name = "carDaoImpl")
    private CarDao carDao;
    
    @Resource(name = "carDaoImpl")
    public void setBaseDao(CarDao carDao)
    {
        super.setBaseDao(carDao);
    }

    @Override
    public Car findCarByDriver(String driver)
    {
       
        return carDao.findCarByDriver(driver);
    }

   
    
}
