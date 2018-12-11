package com.lczx.shiro;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.lczx.entity.Admin;
import com.lczx.service.AdminService;
import com.lczx.util.Setting;
import com.lczx.util.Setting.AccountLockType;
import com.lczx.util.SettingUtils;


/**
 * 项目名称：
 * 功能模块名称：权限认证
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public class AuthRealm extends AuthorizingRealm
{
    
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    
    /**
     * 获取认证信息
     * 
     * @param token
     *            令牌
     * @return 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
    		AuthenticationToken token)
    {
		//UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        String ip = upToken.getHost();
        if (username != null && password != null)
        {
            Admin admin = adminService.findByUsername(username);
            if (admin == null)
            {
                throw new UnknownAccountException();
            }
            if (!admin.getIsEnabled())
            {
                throw new DisabledAccountException();
            }
            Setting setting = SettingUtils.get();
            if (admin.getIsLocked())
            {
                if (ArrayUtils.contains(setting.getAccountLockTypes(),
                        AccountLockType.admin))
                {
                    int loginFailureLockTime = setting.getAccountLockTime();
                    if (loginFailureLockTime == 0)
                    {
                        throw new LockedAccountException();
                    }
                    Date lockedDate = admin.getLockedDate();
                    Date unlockDate = DateUtils.addMinutes(lockedDate,
                            loginFailureLockTime);
                    if (new Date().after(unlockDate))
                    {
                        admin.setLoginFailureCount(0);
                        admin.setIsLocked(false);
                        admin.setLockedDate(null);
                        adminService.update(admin);
                    }
                    else
                    {
                        throw new LockedAccountException();
                    }
                }
                else
                {
                    admin.setLoginFailureCount(0);
                    admin.setIsLocked(false);
                    admin.setLockedDate(null);
                    adminService.update(admin);
                }
            }
            if (!DigestUtils.md5Hex(password).equals(admin.getPassword()))
            {
                int loginFailureCount = admin.getLoginFailureCount() + 1;
                if (loginFailureCount >= setting.getAccountLockCount())
                {
                    admin.setIsLocked(true);
                    admin.setLockedDate(new Date());
                }
                admin.setLoginFailureCount(loginFailureCount);
                adminService.update(admin);
                throw new IncorrectCredentialsException();
            }
            admin.setLoginIp(ip);
            admin.setLoginDate(new Date());
            admin.setLoginFailureCount(0);
            adminService.update(admin);
            return new SimpleAuthenticationInfo(admin, password, getName());
        }
        throw new UnknownAccountException();
    }
    
    /**
     * 获取授权信息
     * 
     * @param principals
     *            principals
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals)
    {
    	//System.out.println("授权");
		//获取登录时输入的用户名
		//String loginName = (String) principals.fromRealm(getName()).iterator().next();
    	Admin user = (Admin)principals.fromRealm(getName()).iterator().next();
		//到数据库查是否有此对象  
        //Admin user = adminService.findByUsername(loginName);
        if (user != null)
        {
        	//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）  
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        	//用户的角色集合
//            info.setRoles(user.getRolesName()); 
            List<String> authorities = adminService.findAuthorities(user.getId());
            if (authorities != null)
            {
            	info.addStringPermissions(authorities);
                return info;
            }
        }
        return null;
    }
    
}