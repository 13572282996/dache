package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.CancelCauseConfigDao;
import com.lczx.entity.CancelCauseConfig;
import com.lczx.service.CancelCauseConfigService;

@Service("cancelCauseServiceImpl")
public class CancelCauseConfigServiceImpl extends
        BaseServiceImpl<CancelCauseConfig, Long> implements
        CancelCauseConfigService
{
    @Resource(name = "cancelCauseConfigDaoImpl")
    private CancelCauseConfigDao cancelCauseConfigDao;
    
    @Resource(name = "cancelCauseConfigDaoImpl")
    public void setBaseDao(CancelCauseConfigDao cancelCauseConfigDao)
    {
        super.setBaseDao(cancelCauseConfigDao);
    }
}
