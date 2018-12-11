package com.lczx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.lczx.dao.CodeDao;
import com.lczx.entity.Code;
import com.lczx.entity.Resource;

@Repository("codeDaoImpl")
public class CodeDaoImpl extends BaseDaoImpl<Code, Long> implements CodeDao
{
    
    @Override
    public List<Code> queryCodes(String mobile)
    {
        String jpql = "select code from Code code  where code.mobile = (:mobile) order by code.createDate desc";
        return entityManager.createQuery(jpql, Code.class)
                .setFlushMode(FlushModeType.COMMIT)
                .setMaxResults(1)
                .setFirstResult(0)
                .setParameter("mobile", mobile)
                .getResultList();
    }
    
}
