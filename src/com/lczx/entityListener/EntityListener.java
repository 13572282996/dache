/*



 */
package com.lczx.entityListener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.lczx.entity.BaseEntity;

/**
 * 项目名称：
 * 功能模块名称：Listener - 创建日期、修改日期处理
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public class EntityListener
{
    
    /**
     * 保存前处理
     * 
     * @param entity
     *            基类
     */
    @PrePersist
    public void prePersist(BaseEntity entity)
    {
        entity.setCreateDate(new Date());
        entity.setModifyDate(new Date());
    }
    
    /**
     * 更新前处理
     * 
     * @param entity
     *            基类
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity)
    {
        entity.setModifyDate(new Date());
    }
    
}