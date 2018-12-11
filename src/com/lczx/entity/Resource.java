/**
 * 文 件 名:  Resource.java
 * 版       权:  Copyright (c) ZenSoftware Co.,Ltd. 2014
 * 描       述: 
 * 修  改   人:yideng
 * 修改时间:Oct 28, 2014
 * 修改描述:
 */
package com.lczx.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 项目名称：
 * 功能模块名称：资源管理
 * 功能描述：
 * @author yideng
 * @version 1.0 Oct 28, 2014
 * Copyright: Copyright (c) ZenSoftware Co.,Ltd. 2014
 * Company:四川天思科技有限责任公司 www.zensoftware.com.cn
 */
@Entity
@Table(name = "cms_resource")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "cms_resource_sequence")
public class Resource extends OrderEntity
{

    /**注释内容 */
    
    /**
     * 
     */
    private static final long serialVersionUID = 8861432398921669678L;

    /**资源组名称 */
    private String groupName;
    
    /**资源名称 */
    private String name;
    
    /**资源标识 */
    private String mark;
    
    /**资源url */
    private String url;
    
    /**角色 */
    private Set<Role> roles = new HashSet<Role>();

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "resources")
    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }
    
}
