package com.lczx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lczx.dao.Order2DriverDao;
import com.lczx.entity.Order2Driver;

@Repository("order2driverDaoImpl")
public class Order2DriverDaoImpl extends BaseDaoImpl<Order2Driver, Long> implements Order2DriverDao
{

    @Override
    public List<Order2Driver> queryOrder2DriversByMobile(String driverPhone)
    {
        if (driverPhone == null)
        {
            return null;
        }
        try
        {
            
            String jpql = "select o2d from Order2Driver o2d where o2d.driverPhone = (:driverPhone) and o2d.status = (:unreceive)";
            return entityManager.createQuery(jpql, Order2Driver.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("driverPhone", driverPhone)
                    .setParameter("unreceive", Order2Driver.Status.unreceive)
                    .getResultList();
        }
        catch (NoResultException e)
        {
            return null;
        }
        
    }

    @Override
    public List<Order2Driver> queryOrder2DriversByOrderId(String orderId)
    {
        if (orderId == null)
        {
            return null;
        }
        try
        {
            
            String jpql = "select o2d from Order2Driver o2d where o2d.orderId = (:orderId) and o2d.status = (:unreceive)";
            return entityManager.createQuery(jpql, Order2Driver.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("orderId", orderId)
                    .setParameter("unreceive", Order2Driver.Status.unreceive)
                    .getResultList();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }
    
    
}
