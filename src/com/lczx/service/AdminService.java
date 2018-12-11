/*



 */
package com.lczx.service;

import java.util.List;

import com.lczx.entity.Admin;


/**
 * 项目名称：
 * 功能模块名称：Service - 管理员
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface AdminService extends BaseService<Admin, Long>
{
    
    /**
     * 判断用户名是否存在
     * 
     * @param username
     *            用户名(忽略大小写)
     * @return 用户名是否存在
     */
    boolean usernameExists(String username);
    
    /**
     * 根据用户名查找管理员
     * 
     * @param username
     *            用户名(忽略大小写)
     * @return 管理员，若不存在则返回null
     */
    Admin findByUsername(String username);
    
    /**
     * 根据ID查找权限
     * 
     * @param id
     *            ID
     * @return 权限,若不存在则返回null
     */
    List<String> findAuthorities(Long id);
    
    /**
     * 判断管理员是否登录
     * 
     * @return 管理员是否登录
     */
    boolean isAuthenticated();
    
    /**
     * 获取当前登录管理员
     * 
     * @return 当前登录管理员,若不存在则返回null
     */
    Admin getCurrent();
    
    /**
     * 获取当前登录用户名
     * 
     * @return 当前登录用户名,若不存在则返回null
     */
    String getCurrentUsername();
    
}