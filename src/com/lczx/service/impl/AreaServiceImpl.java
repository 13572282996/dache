/*



 */
package com.lczx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lczx.dao.AreaDao;
import com.lczx.entity.Area;
import com.lczx.service.AreaService;



/**
 * 项目名称：
 * 功能模块名称：Service - 地区
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Service("areaServiceImpl")
public class AreaServiceImpl extends BaseServiceImpl<Area, Long> implements
        AreaService
{
    
    @Resource(name = "areaDaoImpl")
    private AreaDao areaDao;
    
    @Resource(name = "areaDaoImpl")
    public void setBaseDao(AreaDao areaDao)
    {
        super.setBaseDao(areaDao);
    }
    
    @Transactional(readOnly = true)
    public List<Area> findRoots()
    {
        return areaDao.findRoots(null);
    }
    
    @Transactional(readOnly = true)
    public List<Area> findRoots(Integer count)
    {
        return areaDao.findRoots(count);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "area", allEntries = true)
    public void save(Area area)
    {
        super.save(area);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "area", allEntries = true)
    public Area update(Area area)
    {
        return super.update(area);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "area", allEntries = true)
    public Area update(Area area, String... ignoreProperties)
    {
        return super.update(area, ignoreProperties);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "area", allEntries = true)
    public void delete(Long id)
    {
        super.delete(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "area", allEntries = true)
    public void delete(Long... ids)
    {
        super.delete(ids);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "area", allEntries = true)
    public void delete(Area area)
    {
        super.delete(area);
    }
    
}