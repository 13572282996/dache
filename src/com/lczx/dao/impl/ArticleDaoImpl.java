/*



 */
package com.lczx.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.lczx.dao.ArticleDao;
import com.lczx.entity.Article;
import com.lczx.entity.ArticleCategory;
import com.lczx.util.Filter;
import com.lczx.util.Order;
import com.lczx.util.Page;
import com.lczx.util.Pageable;


/**
 * 项目名称：
 * 功能模块名称：Dao - 文章
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Repository("articleDaoImpl")
public class ArticleDaoImpl extends BaseDaoImpl<Article, Long> implements
        ArticleDao
{
    /**
     * 首页搜索
     * @param search
     *              搜索条件
     * @param pageable
     *              分页信息
     * @return 文章分页
     */
    @Override
    public Page<Article> findPage(String search, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions,
                criteriaBuilder.equal(root.get("isPublication"), true));
        if(search!=null){
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.like(root.<String> get("title"), "%" + search + "%"));
        }else{
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.like(root.<String> get("title"), "%"));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }
    
    public Page<Article> findPage(ArticleCategory articleCategory,Date beginDate,Date endDate, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions,
                criteriaBuilder.equal(root.get("isPublication"), true));
        if (articleCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"),
                            articleCategory),
                            criteriaBuilder.like(root.get("articleCategory")
                                    .<String> get("treePath"), "%"
                                    + ArticleCategory.TREE_PATH_SEPARATOR
                                    + articleCategory.getId()
                                    + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
        }
        if (beginDate != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("timeDate"),
                            beginDate));
        }
        if (endDate != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.lessThan(root.<Date> get("timeDate"),
                            endDate));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }
    
    public List<Article> findActivity()
    {
        String jpql = "select article  from Article article where article.articleCategory = '28' and article.timeDate >= now() order by timeDate desc limit 0,1";
        TypedQuery<Article> query = entityManager.createQuery(jpql,
                Article.class).setFlushMode(FlushModeType.COMMIT);
        query.setMaxResults(6);
        return query.getResultList();
    }
    
    public List<Article> findBeforeActivity()
    {
        String jpql = "select article from Article article where article.articleCategory = '28' and article.timeDate < now()";
        TypedQuery<Article> query = entityManager.createQuery(jpql,
                Article.class).setFlushMode(FlushModeType.COMMIT);
       
        return query.getResultList();
    }
    public List<Article> findList(ArticleCategory articleCategory,
             Integer count, List<Filter> filters,
            List<Order> orders)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions,
                criteriaBuilder.equal(root.get("isPublication"), true));
        if (articleCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"),
                            articleCategory),
                            criteriaBuilder.like(root.get("articleCategory")
                                    .<String> get("treePath"), "%"
                                    + ArticleCategory.TREE_PATH_SEPARATOR
                                    + articleCategory.getId()
                                    + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
        }
       
        criteriaQuery.where(restrictions);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
        return super.findList(criteriaQuery, null, count, filters, orders);
    }
    
    public List<Article> findList(ArticleCategory articleCategory,
            Date beginDate, Date endDate, Integer first, Integer count)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions,
                criteriaBuilder.equal(root.get("isPublication"), true));
        if (articleCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"),
                            articleCategory),
                            criteriaBuilder.like(root.get("articleCategory")
                                    .<String> get("treePath"), "%"
                                    + ArticleCategory.TREE_PATH_SEPARATOR
                                    + articleCategory.getId()
                                    + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
        }
        if (beginDate != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("createDate"),
                            beginDate));
        }
        if (endDate != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.lessThanOrEqualTo(root.<Date> get("createDate"),
                            endDate));
        }
        criteriaQuery.where(restrictions);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
        return super.findList(criteriaQuery, first, count, null, null);
    }
    
    public Page<Article> findPage(ArticleCategory articleCategory,
             Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = criteriaQuery.from(Article.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions,
                criteriaBuilder.equal(root.get("isPublication"), true));
        if (articleCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"),
                            articleCategory),
                            criteriaBuilder.like(root.get("articleCategory")
                                    .<String> get("treePath"), "%"
                                    + ArticleCategory.TREE_PATH_SEPARATOR
                                    + articleCategory.getId()
                                    + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
        }
       
        criteriaQuery.where(restrictions);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
        return super.findPage(criteriaQuery, pageable);
    }

}