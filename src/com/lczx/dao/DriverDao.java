package com.lczx.dao;

import com.lczx.entity.Driver;

public interface DriverDao extends BaseDao<Driver, Long> 
{
    Driver findDriverByMobile(String mobile);
    
    Driver findDriverByToken(String token);
}
