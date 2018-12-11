/*



 */
package com.lczx.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lczx.dao.ArticleDao;
import com.lczx.entity.Article;
import com.lczx.entity.ArticleCategory;
import com.lczx.service.ArticleService;
import com.lczx.service.StaticService;
import com.lczx.util.Filter;
import com.lczx.util.Order;
import com.lczx.util.Page;
import com.lczx.util.Pageable;


/**
 * 项目名称：
 * 功能模块名称：Service - 文章
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Service("articleServiceImpl")
public class ArticleServiceImpl extends BaseServiceImpl<Article, Long>
        implements ArticleService, DisposableBean
{
    
    /** 查看点击数时间 */
    private long viewHitsTime = System.currentTimeMillis();
    
    @Resource(name = "ehCacheManager")
    private CacheManager cacheManager;
    
    @Resource(name = "articleDaoImpl")
    private ArticleDao articleDao;
    
    @Resource(name = "staticServiceImpl")
    private StaticService staticService;
    
    @Resource(name = "articleDaoImpl")
    public void setBaseDao(ArticleDao articleDao)
    {
        super.setBaseDao(articleDao);
    }
    
    @Transactional(readOnly = true)
    public List<Article> findActivity()
    { 
        return articleDao.findActivity();
    }
    
    @Transactional(readOnly = true)
    public List<Article> findBeforeActivity()
    { 
        return articleDao.findBeforeActivity();
    }
    
   @Transactional(readOnly = true)
    public List<Article> findList(ArticleCategory articleCategory,
            Integer count, List<Filter> filters,
            List<Order> orders)
    { 
        return articleDao.findList(articleCategory,
                count,
                filters,
                orders);
    }
    
    @Transactional(readOnly = true)
    @Cacheable("article")
    public List<Article> findList(ArticleCategory articleCategory,
             Integer count, List<Filter> filters,
            List<Order> orders, String cacheRegion)
    {
        return articleDao.findList(articleCategory,
                count,
                filters,
                orders);
    }
    
    @Transactional(readOnly = true)
    public List<Article> findList(ArticleCategory articleCategory,
            Date beginDate, Date endDate, Integer first, Integer count)
    {
        return articleDao.findList(articleCategory,
                beginDate,
                endDate,
                first,
                count);
    }
    
    @Transactional(readOnly = true)
    public Page<Article> findPage(ArticleCategory articleCategory,
           Pageable pageable)
    {
        return articleDao.findPage(articleCategory,  pageable);
    }
    
    public long viewHits(Long id)
    {
        Ehcache cache = cacheManager.getEhcache(Article.HITS_CACHE_NAME);
        Element element = cache.get(id);
        Long hits;
        if (element != null)
        {
            hits = (Long) element.getObjectValue();
        }
        else
        {
            Article article = articleDao.find(id);
            if (article == null)
            {
                return 0L;
            }
            hits = article.getHits();
        }
        hits++;
        cache.put(new Element(id, hits));
        long time = System.currentTimeMillis();
        if (time > viewHitsTime + Article.HITS_CACHE_INTERVAL)
        {
            viewHitsTime = time;
            updateHits();
            cache.removeAll();
        }
        return hits;
    }
    
    public void destroy() throws Exception
    {
        updateHits();
    }
    
    /**
     * 更新点击数
     */
    @SuppressWarnings("unchecked")
    private void updateHits()
    {
        Ehcache cache = cacheManager.getEhcache(Article.HITS_CACHE_NAME);
        List<Long> ids = cache.getKeys();
        for (Long id : ids)
        {
            Article article = articleDao.find(id);
            if (article != null)
            {
                Element element = cache.get(id);
                long hits = (Long) element.getObjectValue();
                article.setHits(hits);
                articleDao.merge(article);
            }
        }
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
    public void save(Article article)
    {
        Assert.notNull(article);
        
        super.save(article);
        articleDao.flush();
        staticService.build(article);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
    public Article update(Article article)
    {
        Assert.notNull(article);
        
        Article pArticle = super.update(article);
        articleDao.flush();
        staticService.build(pArticle);
        return pArticle;
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
    public Article update(Article article, String... ignoreProperties)
    {
        return super.update(article, ignoreProperties);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
    public void delete(Long id)
    {
        super.delete(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
    public void delete(Long... ids)
    {
        super.delete(ids);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = { "article", "articleCategory" }, allEntries = true)
    public void delete(Article article)
    {
        if (article != null)
        {
            staticService.delete(article);
        }
        super.delete(article);
    }

    /**
     * @param articleCategory
     * @param beginDate 正在进行的活动
     * @param endDate 过期活动
     * @param pageable
     * @return
     */
    @Override
    public Page<Article> findPage(ArticleCategory articleCategory,
            Date beginDate, Date endDate, Pageable pageable)
    {
        return articleDao.findPage(articleCategory, beginDate, endDate, pageable);
    }

    /**
     * 首页搜索及分页
     * @param search
     *          搜索条件
     * @param pageable
     *           分页
     * @return 文章分页
     */
    @Override
    public Page<Article> findPage(String search, Pageable pageable)
    {
        return articleDao.findPage(search, pageable);
    }
    
}