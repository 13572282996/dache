package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.DriverDao;
import com.lczx.entity.Driver;
import com.lczx.service.DriverService;

@Service("driverServiceImpl")
public class DriverServiceImpl extends BaseServiceImpl<Driver, Long> implements
        DriverService
{
    @Resource(name = "driverDaoImpl")
    private DriverDao dirverDao;
    
    @Resource(name = "driverDaoImpl")
    public void setBaseDao(DriverDao dirverDao)
    {
        super.setBaseDao(dirverDao);
    }

    @Override
    public Driver findDriverByMobile(String mobile)
    {
        return dirverDao.findDriverByMobile(mobile);
    }

    @Override
    public Driver findDriverByToken(String token)
    {
        return dirverDao.findDriverByToken(token);
    }
}
