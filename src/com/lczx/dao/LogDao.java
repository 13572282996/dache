/*



 */
package com.lczx.dao;

import com.lczx.entity.Log;


/**
 * 项目名称：
 * 功能模块名称：Dao - 日志
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface LogDao extends BaseDao<Log, Long>
{
    
    /**
     * 删除所有日志
     */
    void removeAll();
    
}
