/*



 */
package com.lczx.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lczx.dao.AdminDao;
import com.lczx.entity.Admin;


/**
 * 项目名称：
 * 功能模块名称：Dao - 管理员
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long> implements AdminDao
{
    
    public boolean usernameExists(String username)
    {
        if (username == null)
        {
            return false;
        }
        String jpql = "select count(*) from Admin admin where lower(admin.username) = lower(:username)";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setFlushMode(FlushModeType.COMMIT)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }
    
    public Admin findByUsername(String username)
    {
        if (username == null)
        {
            return null;
        }
        try
        {
            String jpql = "select admin from Admin admin where lower(admin.username) = lower(:username)";
            return entityManager.createQuery(jpql, Admin.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("username", username)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }
    
}