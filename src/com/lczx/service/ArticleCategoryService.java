/*



 */
package com.lczx.service;

import java.util.List;

import com.lczx.entity.ArticleCategory;


/**
 * 项目名称：
 * 功能模块名称：Service - 文章分类
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface ArticleCategoryService extends
        BaseService<ArticleCategory, Long>
{
    
    /**
     * 查找顶级文章分类
     * 
     * @return 顶级文章分类
     */
    List<ArticleCategory> findRoots();
    
    /**
     * 查找顶级文章分类
     * 
     * @param count
     *            数量
     * @return 顶级文章分类
     */
    List<ArticleCategory> findRoots(Integer count);
    
    /**
     * 查找顶级文章分类(缓存)
     * 
     * @param count
     *            数量
     * @param cacheRegion
     *            缓存区域
     * @return 顶级文章分类(缓存)
     */
    List<ArticleCategory> findRoots(Integer count, String cacheRegion);
    
    /**
     * 查找上级文章分类
     * 
     * @param articleCategory
     *            文章分类
     * @return 上级文章分类
     */
    List<ArticleCategory> findParents(ArticleCategory articleCategory);
    
    /**
     * 查找上级文章分类
     * 
     * @param articleCategory
     *            文章分类
     * @param count
     *            数量
     * @return 上级文章分类
     */
    List<ArticleCategory> findParents(ArticleCategory articleCategory,
            Integer count);
    
    /**
     * 查找上级文章分类(缓存)
     * 
     * @param articleCategory
     *            文章分类
     * @param count
     *            数量
     * @param cacheRegion
     *            缓存区域
     * @return 上级文章分类(缓存)
     */
    List<ArticleCategory> findParents(ArticleCategory articleCategory,
            Integer count, String cacheRegion);
    
    /**
     * 查找文章分类树
     * 
     * @return 文章分类树
     */
    List<ArticleCategory> findTree();
    
    /**
     * 查找下级文章分类
     * 
     * @param articleCategory
     *            文章分类
     * @return 下级文章分类
     */
    List<ArticleCategory> findChildren(ArticleCategory articleCategory);
    
    /**
     * 查找下级文章分类
     * 
     * @param articleCategory
     *            文章分类
     * @param count
     *            数量
     * @return 下级文章分类
     */
    List<ArticleCategory> findChildren(ArticleCategory articleCategory,
            Integer count);
    
    /**
     * 查找下级文章分类(缓存)
     * 
     * @param articleCategory
     *            文章分类
     * @param count
     *            数量
     * @param cacheRegion
     *            缓存区域
     * @return 下级文章分类(缓存)
     */
    List<ArticleCategory> findChildren(ArticleCategory articleCategory,
            Integer count, String cacheRegion);
    
    /**
     * 查找下级文章分类（只要第一级）
     * 
     * @param articleCategory
     *            文章分类
     *            
     * @return 下级文章分类（只要第一级）
     */
    List<ArticleCategory> findChildrenFirst(ArticleCategory articleCategory);
    
    /**
     * 查找下级文章分类（只要第一级）
     * 
     * @param articleCategory
     *            文章分类
     * @param count
     *            数量
     * @return 下级文章分类（只要第一级）
     */
    List<ArticleCategory> findChildrenFirst(ArticleCategory articleCategory,
            Integer count);
    
    /**
     * 查找下级文章分类（只要第一级）(缓存)
     * 
     * @param articleCategory
     *            文章分类
     * @param count
     *            数量
     * @param cacheRegion
     *            缓存区域    
     * @return 下级文章分类（只要第一级）(缓存)
     */
    List<ArticleCategory> findChildrenFirst(ArticleCategory articleCategory,
            Integer count, String cacheRegion);
    
}