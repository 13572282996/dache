package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.PriceConfigDao;
import com.lczx.entity.PriceConfig;
import com.lczx.service.PriceConfigService;

@Service("priceConfigServiceImpl")
public class PriceConfigServiceImpl extends BaseServiceImpl<PriceConfig, Long>
        implements PriceConfigService
{
    @Resource(name = "priceConfigDaoImpl")
    private PriceConfigDao priceConfigDao;
    
    @Resource(name = "priceConfigDaoImpl")
    public void setBaseDao(PriceConfigDao priceConfigDao)
    {
        super.setBaseDao(priceConfigDao);
    }
}
