package com.lczx.service;

import com.lczx.entity.Driver;

public interface DriverService extends BaseService<Driver, Long>
{
   Driver findDriverByMobile(String mobile);
   
   Driver findDriverByToken(String token);
}
