package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.AdPositionDao;
import com.lczx.dao.MemberDao;
import com.lczx.entity.Member;
import com.lczx.service.MemberService;

@Service("memberServiceImpl")
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService
{
    
    @Resource(name = "memberDaoImpl")
    private MemberDao memberDao;
    
    @Resource(name = "memberDaoImpl")
    public void setBaseDao(MemberDao memberDao)
    {
        super.setBaseDao(memberDao);
    }
    @Override
    public boolean mobileExists(String mobile)
    {
        return memberDao.mobileExists(mobile);
    }

    @Override
    public Member findMemberByMobile(String mobile)
    {
        return memberDao.findMemberByMobile(mobile);
    }
    @Override
    public Member findMemberByToken(String token)
    {
        return memberDao.findMemberByToken(token);
    }
}
