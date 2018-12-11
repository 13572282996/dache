/*



 */
package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.LogDao;
import com.lczx.entity.Log;
import com.lczx.service.LogService;



/**
 * 项目名称：
 * 功能模块名称：Service - 日志
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, Long> implements
        LogService
{
    
    @Resource(name = "logDaoImpl")
    private LogDao logDao;
    
    @Resource(name = "logDaoImpl")
    public void setBaseDao(LogDao logDao)
    {
        super.setBaseDao(logDao);
    }
    
    public void clear()
    {
        logDao.removeAll();
    }
    
}