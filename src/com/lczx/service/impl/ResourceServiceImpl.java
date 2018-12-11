/**
 * 文 件 名:  ResourceServiceImpl.java
 * 版       权:  Copyright (c) ZenSoftware Co.,Ltd. 2014
 * 描       述: 
 * 修  改   人:yideng
 * 修改时间:Oct 28, 2014
 * 修改描述:
 */
package com.lczx.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lczx.dao.ResourceDao;
import com.lczx.entity.Resource;
import com.lczx.service.ResourceService;


/**
 * 项目名称：
 * 功能模块名称：资源管理
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Oct 28, 2014
 * Copyright: Copyright (c) ZenSoftware Co.,Ltd. 2014
 * Company:四川天思科技有限责任公司 www.zensoftware.com.cn
 */
@Service("resourceServiceImpl")
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Long>
        implements ResourceService
{
    @javax.annotation.Resource(name = "resourceDaoImpl")
    public void setBase(ResourceDao resourceDao)
    {
        super.setBaseDao(resourceDao);
    }
    
    @javax.annotation.Resource(name = "resourceDaoImpl")
    ResourceDao resourceDao;
    
    /**
     * @return
     */
    @Override
    public List<Resource> findResourceGroup()
    {
        return resourceDao.findResourceGroup();
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "authorization" }, allEntries = true)
    public void save(Resource entity)
    {
        super.save(entity);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "authorization" }, allEntries = true)
    public Resource update(Resource entity)
    {
        return super.update(entity);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "authorization" }, allEntries = true)
    public Resource update(Resource entity, String... ignoreProperties)
    {
        return super.update(entity, ignoreProperties);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "authorization" }, allEntries = true)
    public void delete(Long... ids)
    {
        super.delete(ids);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "authorization" }, allEntries = true)
    public void delete(Long id)
    {
        super.delete(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "authorization" }, allEntries = true)
    public void delete(Resource resource)
    {
        super.delete(resource);
    }
    
    /**
     * @param previousName
     * @param currentName
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public boolean nameUnique(String previousName, String currentName)
    {
        if (StringUtils.equalsIgnoreCase(previousName, currentName))
        {
            return true;
        }
        else
        {
            if (resourceDao.nameExists(currentName))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }
    
    /**
     * @param previousMark
     * @param currentMark
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public boolean markUnique(String previousMark, String currentMark)
    {
        if (StringUtils.equalsIgnoreCase(previousMark, currentMark))
        {
            return true;
        }
        else
        {
            if (resourceDao.markExists(currentMark))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }
    
    /**
     * @param previousUrl
     * @param currentUrl
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public boolean resourceUrlUnique(String previousUrl, String currentUrl)
    {
        if (StringUtils.equalsIgnoreCase(previousUrl, currentUrl))
        {
            return true;
        }
        else
        {
            if (resourceDao.resourceUrlExists(currentUrl))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }
    
}
