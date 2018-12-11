package com.lczx.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lczx.dao.DriverDao;
import com.lczx.entity.Driver;
@Repository("driverDaoImpl")
public class DriverDaoImpl extends BaseDaoImpl<Driver, Long> implements DriverDao
{

    @Override
    public Driver findDriverByMobile(String mobile)
    {
        if (mobile == null)
        {
            return null;
        }
        try
        {
            String jpql = "select driver from Driver driver where driver.driverPhone = (:mobile)";
            return entityManager.createQuery(jpql, Driver.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("mobile", mobile)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Driver findDriverByToken(String token)
    {
        if (token == null)
        {
            return null;
        }
        try
        {
            String jpql = "select driver from Driver driver where driver.token = (:token)";
            return entityManager.createQuery(jpql, Driver.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("token", token)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }
    
}
