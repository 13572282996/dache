/*



 */
package com.lczx.service.impl;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;





import com.lczx.service.CacheService;
import com.lczx.util.SettingUtils;

import freemarker.template.TemplateModelException;

/**
 * 项目名称：
 * 功能模块名称：Service - 缓存
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Service("cacheServiceImpl")
public class CacheServiceImpl implements CacheService
{
    
    @Resource(name = "ehCacheManager")
    private CacheManager cacheManager;
    
    @Resource(name = "messageSource")
    private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
    
    @Resource(name = "freemarkerConfig")
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
    public String getDiskStorePath()
    {
        return cacheManager.getConfiguration()
                .getDiskStoreConfiguration()
                .getPath();
    }
    
    public int getCacheSize()
    {
        int cacheSize = 0;
        String[] cacheNames = cacheManager.getCacheNames();
        if (cacheNames != null)
        {
            for (String cacheName : cacheNames)
            {
                Ehcache cache = cacheManager.getEhcache(cacheName);
                if (cache != null)
                {
                    cacheSize += cache.getSize();
                }
            }
        }
        return cacheSize;
    }
    
    @CacheEvict(value = { "setting", "authorization", "logConfig", "template",
            "area", "adPosition", "navigation", "friendLink",
            "article", "articleCategory" }, allEntries = true)
    public void clear()
    {
        reloadableResourceBundleMessageSource.clearCache();
        try
        {
            freeMarkerConfigurer.getConfiguration()
                    .setSharedVariable("setting", SettingUtils.get());
        }
        catch (TemplateModelException e)
        {
            e.printStackTrace();
        }
        freeMarkerConfigurer.getConfiguration().clearTemplateCache();
    }
    
}