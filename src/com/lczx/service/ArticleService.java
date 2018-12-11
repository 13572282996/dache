/*



 */
package com.lczx.service;

import java.util.Date;
import java.util.List;

import com.lczx.entity.Article;
import com.lczx.entity.ArticleCategory;
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
public interface ArticleService extends BaseService<Article, Long>
{
    
    /**
     * 查找文章
     * 
     * @param articleCategory
     *            文章分类
     * @param tags
     *            标签
     * @param count
     *            数量
     * @param filters
     *            筛选
     * @param orders
     *            排序
     * @return 仅包含已发布文章
     */
    List<Article> findList(ArticleCategory articleCategory,
            Integer count, List<Filter> filters, List<Order> orders);
    
    /**
     * 查找文章(缓存)
     * 
     * @param articleCategory
     *            文章分类
     * @param tags
     *            标签
     * @param count
     *            数量
     * @param filters
     *            筛选
     * @param orders
     *            排序
     * @param cacheRegion
     *            缓存区域
     * @return 仅包含已发布文章
     */
    List<Article> findList(ArticleCategory articleCategory, 
            Integer count, List<Filter> filters, List<Order> orders,
            String cacheRegion);
    
    /**
     * 查找文章
     * 
     * @param articleCategory
     *            文章分类
     * @param beginDate
     *            起始日期
     * @param endDate
     *            结束日期
     * @param first
     *            起始记录
     * @param count
     *            数量
     * @return 仅包含已发布文章
     */
    List<Article> findList(ArticleCategory articleCategory, Date beginDate,
            Date endDate, Integer first, Integer count);
    
    /**
     * 查找文章分页
     * 
     * @param articleCategory
     *            文章分类
     * @param tags
     *            标签
     * @param pageable
     *            分页信息
     * @return 仅包含已发布文章
     */
    Page<Article> findPage(ArticleCategory articleCategory, 
            Pageable pageable);
    
    /**
     * 查看并更新点击数
     * 
     * @param id
     *            ID
     * @return 点击数
     */
    long viewHits(Long id);
    
    List<Article> findActivity();
    
    List<Article> findBeforeActivity();
    
    /** 
     * 活动分页
     *@param beginDate
     *           正在进行的活动
     *@param  endDate
     *           过期活动
     * @param pageable
      *            分页信息
      * @return 文章分页
     */
    Page<Article> findPage(ArticleCategory articleCategory,Date beginDate,Date endDate, Pageable pageable);
    
    /** 
     * 首页搜索及分页
     *@param search 
     *              搜索条件
     * @param pageable
      *            分页信息
      * @return 文章分页
     */
    Page<Article> findPage(String search,Pageable pageable);   
    
}