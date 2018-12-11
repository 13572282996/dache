/*



 */
package com.lczx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;






import org.springframework.stereotype.Repository;

import com.lczx.dao.FriendLinkDao;
import com.lczx.entity.FriendLink;
import com.lczx.entity.FriendLink.Type;

/**
 * 项目名称：
 * 功能模块名称：Dao - 友情链接
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Repository("friendLinkDaoImpl")
public class FriendLinkDaoImpl extends BaseDaoImpl<FriendLink, Long> implements
        FriendLinkDao
{
    
    public List<FriendLink> findList(Type type)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FriendLink> criteriaQuery = criteriaBuilder.createQuery(FriendLink.class);
        Root<FriendLink> root = criteriaQuery.from(FriendLink.class);
        criteriaQuery.select(root);
        if (type != null)
        {
            criteriaQuery.where(criteriaBuilder.equal(root.get("type"), type));
        }
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
        return entityManager.createQuery(criteriaQuery)
                .setFlushMode(FlushModeType.COMMIT)
                .getResultList();
    }
    
}