package com.lczx.dao.impl;

import org.springframework.stereotype.Repository;

import com.lczx.dao.PriceConfigDao;
import com.lczx.entity.PriceConfig;
@Repository("priceConfigDaoImpl")
public class PriceConfigDaoImpl extends BaseDaoImpl<PriceConfig, Long> implements PriceConfigDao
{
    
}
