/*



 */
package com.lczx.service;

import com.lczx.entity.Log;


/**
 * 项目名称：
 * 功能模块名称：Service - 日志
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface LogService extends BaseService<Log, Long>
{
    
    /**
     * 清空日志
     */
    void clear();
    
}