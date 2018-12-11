package com.lczx.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lczx.dao.CarDao;
import com.lczx.entity.Car;
import com.lczx.entity.Member;
@Repository("carDaoImpl")
public class CarDaoImpl extends BaseDaoImpl<Car, Long> implements CarDao
{

    @Override
    public Car findCarByDriver(String driver)
    {
        if (driver == null)
        {
            return null;
        }
        try
        {
            String jpql = "select car from Car car where lower(car.driverPhone) = lower(:driver)";
            return entityManager.createQuery(jpql, Car.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("driver", driver)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }
    
}
