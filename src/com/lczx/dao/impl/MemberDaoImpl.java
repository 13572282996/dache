package com.lczx.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lczx.dao.MemberDao;
import com.lczx.entity.Admin;
import com.lczx.entity.Member;
/***
 * 乘客 
 * 
 * 
 **/
@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao
{

    @Override
    public boolean mobileExists(String mobile)
    {
        if (mobile == null) {
            return false;
        }
        String jpql = "select count(*) from Member members where lower(members.mobilePhone) = lower(:mobilePhone)";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("mobilePhone", mobile).getSingleResult();
        return count > 0;
    }

    @Override
    public Member findMemberByMobile(String mobile)
    {

        if (mobile == null)
        {
            return null;
        }
        try
        {
            String jpql = "select members from Member members where lower(members.mobilePhone) = lower(:mobilePhone)";
            return entityManager.createQuery(jpql, Member.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("mobilePhone", mobile)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public Member findMemberByToken(String token)
    {
        if (token == null)
        {
            return null;
        }
        try
        {
            String jpql = "select members from Member members where members.token = (:token)";
            return entityManager.createQuery(jpql, Member.class)
                    .setFlushMode(FlushModeType.COMMIT)
                    .setParameter("token", token)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }
    
}
