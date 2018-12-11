/*



 */
package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lczx.dao.AdDao;
import com.lczx.entity.Ad;
import com.lczx.service.AdService;



/**
 * 项目名称：
 * 功能模块名称：Service - 广告
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Service("adServiceImpl")
public class AdServiceImpl extends BaseServiceImpl<Ad, Long> implements
        AdService
{
    
    @Resource(name = "adDaoImpl")
    public void setBaseDao(AdDao adDao)
    {
        super.setBaseDao(adDao);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "adPosition", allEntries = true)
    public void save(Ad ad)
    {
        super.save(ad);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "adPosition", allEntries = true)
    public Ad update(Ad ad)
    {
        return super.update(ad);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "adPosition", allEntries = true)
    public Ad update(Ad ad, String... ignoreProperties)
    {
        return super.update(ad, ignoreProperties);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "adPosition", allEntries = true)
    public void delete(Long id)
    {
        super.delete(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "adPosition", allEntries = true)
    public void delete(Long... ids)
    {
        super.delete(ids);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "adPosition", allEntries = true)
    public void delete(Ad ad)
    {
        super.delete(ad);
    }
    
}