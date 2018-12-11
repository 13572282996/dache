/**
 * 文 件 名:  ResourceDaoImpl.java
 * 版       权:  Copyright (c) ZenSoftware Co.,Ltd. 2014
 * 描       述: 
 * 修  改   人:yideng
 * 修改时间:Oct 28, 2014
 * 修改描述:
 */
package com.lczx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.lczx.dao.ResourceDao;
import com.lczx.entity.Resource;


/**
 * 项目名称：
 * 功能模块名称：资源管理
 * 功能描述：
 * @author yideng
 * @version 1.0 Oct 28, 2014
 * Copyright: Copyright (c) ZenSoftware Co.,Ltd. 2014
 * Company:四川天思科技有限责任公司 www.zensoftware.com.cn
 */
@Repository("resourceDaoImpl")
public class ResourceDaoImpl extends BaseDaoImpl<Resource, Long> implements
        ResourceDao
{

    /**
     * @return
     */
    @Override
    public List<Resource> findResourceGroup()
    {
        String jpql = "select resource from Resource resource group by resource.groupName order by resource.id asc";
        return entityManager.createQuery(jpql, Resource.class)
        .setFlushMode(FlushModeType.COMMIT)
        .getResultList();
    }

    /**
     * @param name
     * @return
     */
    @Override
    public boolean nameExists(String name)
    {
        if (name == null)
        {
            return false;
        }
        String jpql = "select count(*) from Resource resource where lower(resource.name) = lower(:name)";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setFlushMode(FlushModeType.COMMIT)
                .setParameter("name", name)
                .getSingleResult();
        return count > 0;
    }
    
    /**
     * @param mark
     * @return
     */
    @Override
    public boolean markExists(String mark)
    {
        if (mark == null)
        {
            return false;
        }
        String jpql = "select count(*) from Resource resource where lower(resource.mark) = lower(:mark)";
        Long count = entityManager.createQuery(jpql, Long.class)
        .setFlushMode(FlushModeType.COMMIT)
        .setParameter("mark", mark)
        .getSingleResult();
        return count > 0;
    }

    /**
     * @param url
     * @return
     */
    @Override
    public boolean resourceUrlExists(String url)
    {
        if (url == null)
        {
            return false;
        }
        String jpql = "select count(*) from Resource resource where lower(resource.url) = lower(:url)";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setFlushMode(FlushModeType.COMMIT)
                .setParameter("url", url)
                .getSingleResult();
        return count > 0;
    }
    
}
