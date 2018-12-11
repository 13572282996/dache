/*



 */
package com.lczx.service.impl;

import java.util.List;

import javax.annotation.Resource;









import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lczx.dao.FriendLinkDao;
import com.lczx.entity.FriendLink;
import com.lczx.entity.FriendLink.Type;
import com.lczx.service.FriendLinkService;
import com.lczx.util.Filter;
import com.lczx.util.Order;

/**
 * 项目名称：
 * 功能模块名称：Service - 友情链接
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Service("friendLinkServiceImpl")
public class FriendLinkServiceImpl extends BaseServiceImpl<FriendLink, Long>
        implements FriendLinkService
{
    
    @Resource(name = "friendLinkDaoImpl")
    public FriendLinkDao friendLinkDao;
    
    @Resource(name = "friendLinkDaoImpl")
    public void setBaseDao(FriendLinkDao friendLinkDao)
    {
        super.setBaseDao(friendLinkDao);
    }
    
    @Transactional(readOnly = true)
    public List<FriendLink> findList(Type type)
    {
        return friendLinkDao.findList(type);
    }
    
    @Transactional(readOnly = true)
    @Cacheable("friendLink")
    public List<FriendLink> findList(Integer count, List<Filter> filters,
            List<Order> orders, String cacheRegion)
    {
        return friendLinkDao.findList(null, count, filters, orders);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "friendLink", allEntries = true)
    public void save(FriendLink friendLink)
    {
        super.save(friendLink);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "friendLink", allEntries = true)
    public FriendLink update(FriendLink friendLink)
    {
        return super.update(friendLink);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "friendLink", allEntries = true)
    public FriendLink update(FriendLink friendLink, String... ignoreProperties)
    {
        return super.update(friendLink, ignoreProperties);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "friendLink", allEntries = true)
    public void delete(Long id)
    {
        super.delete(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "friendLink", allEntries = true)
    public void delete(Long... ids)
    {
        super.delete(ids);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "friendLink", allEntries = true)
    public void delete(FriendLink friendLink)
    {
        super.delete(friendLink);
    }
    
}