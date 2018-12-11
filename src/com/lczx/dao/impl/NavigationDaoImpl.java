/*



 */
package com.lczx.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.lczx.dao.NavigationDao;
import com.lczx.entity.ArticleCategory;
import com.lczx.entity.Navigation;
import com.lczx.entity.Navigation.Position;



/**
 * 项目名称：
 * 功能模块名称：Dao - 导航
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Repository("navigationDaoImpl")
public class NavigationDaoImpl extends BaseDaoImpl<Navigation, Long> implements
        NavigationDao
{
    
    public List<Navigation> findList(Position position)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Navigation> criteriaQuery = criteriaBuilder.createQuery(Navigation.class);
        Root<Navigation> root = criteriaQuery.from(Navigation.class);
        criteriaQuery.select(root);
        if (position != null)
        {
            criteriaQuery.where(criteriaBuilder.equal(root.get("position"),
                    position));
        }
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
        return entityManager.createQuery(criteriaQuery)
                .setFlushMode(FlushModeType.COMMIT)
                .getResultList();
    }
    
    /**
     * @param articleCategory
     * @param count
     * @return
     */
    @Override
    public List<Navigation> findChildren(Navigation navigation, Integer count)
    {
        TypedQuery<Navigation> query;
        if (navigation != null)
        {
            String jpql = "select navigation from Navigation navigation where navigation.treePath like :treePath order by navigation.order asc";
            query = entityManager.createQuery(jpql, Navigation.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("treePath",
                            "%" + Navigation.TREE_PATH_SEPARATOR
                                    + navigation.getId()
                                    + ArticleCategory.TREE_PATH_SEPARATOR + "%");
        }
        else
        {
            String jpql = "select navigation from Navigation navigation order by navigation.order asc";
            query = entityManager.createQuery(jpql, Navigation.class)
                    .setFlushMode(FlushModeType.COMMIT);
        }
        if (count != null)
        {
            query.setMaxResults(count);
        }
        return sort(query.getResultList(), navigation);
    }
    
    /**
     * @param articleCategory
     * @param count
     * @return
     */
    @Override
    public List<Navigation> findParentByPosition(Position position)
    {
        TypedQuery<Navigation> query;
        if (position != null)
        {
            try
            {
                String jpql = "select navigation from Navigation navigation where navigation.position=:position and navigation.parent is null order by navigation.order asc";
                query = entityManager.createQuery(jpql, Navigation.class)
                        .setFlushMode(FlushModeType.COMMIT)
                        .setParameter("position", position);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            return null;
        }
        return query.getResultList();
    }
    
    /**
     * 排序导航
     * 
     * @param articleCategories
     *            导航
     * @param parent
     *            上级导航
     * @return 导航
     */
    private List<Navigation> sort(List<Navigation> navigations,
            Navigation parent)
    {
        List<Navigation> result = new ArrayList<Navigation>();
        if (navigations != null)
        {
            for (Navigation navigation : navigations)
            {
                if ((navigation.getParent() != null && navigation.getParent()
                        .equals(parent))
                        || (navigation.getParent() == null && parent == null))
                {
                    result.add(navigation);
                    result.addAll(sort(navigations, navigation));
                }
            }
        }
        return result;
    }
    
    /**
     * 设置treePath、grade并保存
     * 
     * @param articleCategory
     *            文章分类
     */
    @Override
    public void persist(Navigation articleCategory)
    {
        Assert.notNull(articleCategory);
        setValue(articleCategory);
        super.persist(articleCategory);
    }
    
    /**
     * 设置treePath、grade并更新
     * 
     * @param articleCategory
     *            文章分类
     * @return 文章分类
     */
    @Override
    public Navigation merge(Navigation navigation)
    {
        Assert.notNull(navigation);
        setValue(navigation);
        for (Navigation temp : findChildren(navigation, null))
        {
            setValue(temp);
        }
        return super.merge(navigation);
    }
    
    /**
     * 设置值
     * 
     * @param articleCategory
     *            文章分类
     */
    private void setValue(Navigation navigation)
    {
        if (navigation == null)
        {
            return;
        }
        Navigation parent = navigation.getParent();
        if (parent != null)
        {
            navigation.setTreePath(parent.getTreePath() + parent.getId()
                    + ArticleCategory.TREE_PATH_SEPARATOR);
        }
        else
        {
            navigation.setTreePath(ArticleCategory.TREE_PATH_SEPARATOR);
        }
        navigation.setGrade(navigation.getTreePaths().size());
    }
}