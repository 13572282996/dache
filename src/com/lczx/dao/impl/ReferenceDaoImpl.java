/**
 * 文   件  名:  ReferenceDaoImpl.java
 * 描          述：<功能模块名称>
 * 修   改  人:  Administrator
 * 修改时间:  2015年12月10日
 *         <修改描述:>
 * @author Administrator
 * @version [1.0 2015年12月10日]
 * Copyright: Copyright (c) LCZXTECH Co.,Ltd. 2015
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
package com.lczx.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lczx.dao.ReferenceDao;
import com.lczx.entity.Reference;



/**
 * dao-备考专区
 */
@Repository("referenceDaoImpl")
public class ReferenceDaoImpl extends BaseDaoImpl<Reference, Long> implements ReferenceDao
{
    public Reference findByReferenceClass(String referenceClass)
    {
        if (referenceClass == null)
        {
            return null;
        }
        try
        {
            String jpql = "select references from Reference references where lower(references.referenceClass) = lower(:referenceClass)";
            return entityManager.createQuery(jpql, Reference.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("referenceClass", referenceClass)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }
}
