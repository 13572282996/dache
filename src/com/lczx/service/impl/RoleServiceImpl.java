/*



 */
package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lczx.dao.RoleDao;
import com.lczx.entity.Role;
import com.lczx.service.RoleService;


/**
 * 项目名称：
 * 功能模块名称：Service - 角色
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements
        RoleService
{
    
    @Resource(name = "roleDaoImpl")
    public void setBaseDao(RoleDao roleDao)
    {
        super.setBaseDao(roleDao);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void save(Role role)
    {
        super.save(role);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Role update(Role role)
    {
        return super.update(role);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Role update(Role role, String... ignoreProperties)
    {
        return super.update(role, ignoreProperties);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Long id)
    {
        super.delete(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Long... ids)
    {
        super.delete(ids);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Role role)
    {
        super.delete(role);
    }
    
}