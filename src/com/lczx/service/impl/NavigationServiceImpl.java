/*



 */
package com.lczx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lczx.dao.NavigationDao;
import com.lczx.entity.Navigation;
import com.lczx.entity.Navigation.Position;
import com.lczx.service.NavigationService;
import com.lczx.util.Filter;
import com.lczx.util.Order;



/**
 * 项目名称：
 * 功能模块名称：Service - 导航
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Service("navigationServiceImpl")
public class NavigationServiceImpl extends BaseServiceImpl<Navigation, Long>
        implements NavigationService
{
    
    @Resource(name = "navigationDaoImpl")
    private NavigationDao navigationDao;
    
    @Resource(name = "navigationDaoImpl")
    public void setBaseDao(NavigationDao navigationDao)
    {
        super.setBaseDao(navigationDao);
    }
    
    @Transactional(readOnly = true)
    public List<Navigation> findList(Position position)
    {
        return navigationDao.findList(position);
    }
    
    @Transactional(readOnly = true)
    @Cacheable("navigation")
    public List<Navigation> findList(Integer count, List<Filter> filters,
            List<Order> orders, String cacheRegion)
    {
        return navigationDao.findList(null, count, filters, orders);
    }
    
    @Transactional(readOnly = true)
    public List<Navigation> findTree()
    {
        return navigationDao.findChildren(null, null);
    }
    
    /**
     * @param top
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<Navigation> findTree(Position top)
    {
        return navigationDao.findParentByPosition(top);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "navigation", allEntries = true)
    public void save(Navigation navigation)
    {
        super.save(navigation);
    }
    
    @Transactional(readOnly = true)
    public List<Navigation> findChildren(Navigation navigation)
    {
        return navigationDao.findChildren(navigation, null);
    }
    
    @Transactional(readOnly = true)
    public List<Navigation> findChildren(Navigation navigation, Integer count)
    {
        return navigationDao.findChildren(navigation, count);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "navigation", allEntries = true)
    public Navigation update(Navigation navigation)
    {
        return super.update(navigation);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "navigation", allEntries = true)
    public Navigation update(Navigation navigation, String... ignoreProperties)
    {
        return super.update(navigation, ignoreProperties);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "navigation", allEntries = true)
    public void delete(Long id)
    {
        super.delete(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "navigation", allEntries = true)
    public void delete(Long... ids)
    {
        super.delete(ids);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "navigation", allEntries = true)
    public void delete(Navigation navigation)
    {
        super.delete(navigation);
    }
    
}