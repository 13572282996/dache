package com.lczx.dao.impl;

import org.springframework.stereotype.Repository;

import com.lczx.dao.CancelCauseConfigDao;
import com.lczx.entity.CancelCauseConfig;

@Repository("cancelCauseConfigDaoImpl")
public class CancelCauseConfigDaoImpl extends
        BaseDaoImpl<CancelCauseConfig, Long> implements CancelCauseConfigDao
{
    
}
