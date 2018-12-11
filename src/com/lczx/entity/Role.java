/*



 */
package com.lczx.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 项目名称：
 * 功能模块名称：Entity - 角色
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Entity
@Table(name = "cms_role")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "cms_role_sequence")
public class Role extends BaseEntity
{
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 1208675234834789622L;

    /** 名称 */
    private String name;
    
    /** 是否内置 */
    private Boolean isSystem;
    
    /** 描述 */
    private String description;
    
    /** 资源 */
    private List<Resource> resources = new ArrayList<Resource>();
    
    /** 管理员 */
    private Set<Admin> admins = new HashSet<Admin>();
    
    /**
     * 获取名称
     * 
     * @return 名称
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getName()
    {
        return name;
    }
    
    /**
     * 设置名称
     * 
     * @param name
     *            名称
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * 获取是否内置
     * 
     * @return 是否内置
     */
    @Column(nullable = false, updatable = false)
    public Boolean getIsSystem()
    {
        return isSystem;
    }
    
    /**
     * 设置是否内置
     * 
     * @param isSystem
     *            是否内置
     */
    public void setIsSystem(Boolean isSystem)
    {
        this.isSystem = isSystem;
    }
    
    /**
     * 获取描述
     * 
     * @return 描述
     */
    @Length(max = 200)
    public String getDescription()
    {
        return description;
    }
    
    /**
     * 设置描述
     * 
     * @param description
     *            描述
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cms_role_resource")
    public List<Resource> getResources()
    {
        return resources;
    }
    
    public void setResources(List<Resource> resources)
    {
        this.resources = resources;
    }
    
    /**
     * 获取管理员
     * 
     * @return 管理员
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    public Set<Admin> getAdmins()
    {
        return admins;
    }
    
    /**
     * 设置管理员
     * 
     * @param admins
     *            管理员
     */
    public void setAdmins(Set<Admin> admins)
    {
        this.admins = admins;
    }
    
}