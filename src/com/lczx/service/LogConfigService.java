/*



 */
package com.lczx.service;

import java.util.List;

import com.lczx.entity.LogConfig;


/**
 * 项目名称：
 * 功能模块名称：Service - 日志配置
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface LogConfigService
{
    
    /**
     * 获取所有日志配置
     * 
     * @return 所有日志配置
     */
    List<LogConfig> getAll();
    
}