package com.lczx.service;

import com.lczx.entity.Member;

public interface MemberService extends BaseService<Member, Long>
{
    /**
     * 判断mobile是否存在
     * 
     * @param mobile
     *            mobile(忽略大小写)
     * @return mobile是否存在
     */
    boolean mobileExists(String mobile);
    
    Member findMemberByMobile(String mobile);
    
    Member findMemberByToken(String token);
}
