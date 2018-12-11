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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 项目名称：
 * 功能模块名称：Entity - 导航
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Entity
@Table(name = "cms_navigation")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "cms_navigation_sequence")
public class Navigation extends OrderEntity
{
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 1858059690995889800L;

    /** 树路径分隔符 */
    public static final String TREE_PATH_SEPARATOR = ",";
    
    /**
     * 位置
     */
    public enum Position
    {
        
        /** 顶部 */
        top,
        
        /** 中间 */
        middle,
        
        /** 底部 */
        bottom
    }
    
    /** 名称 */
    private String name;
    
    /** 位置 */
    private Position position;
    
    /** 链接地址 */
    private String url;
    
    /** 是否新窗口打开 */
    private Boolean isBlankTarget;
    
    /** 树路径 */
    private String treePath;
    
    /** 层级 */
    private Integer grade;
    
    /** 上级导航 */
    private Navigation parent;
    
    /** 下级导航 */
    private Set<Navigation> children = new HashSet<Navigation>();
    
    /**
     * @return 返回 parent
     */
    @ManyToOne
    public Navigation getParent()
    {
        return parent;
    }
    
    /**
     * @return 返回 treePath
     */
    @Column(nullable = false)
    public String getTreePath()
    {
        return treePath;
    }
    
    /**
     * @param 对treePath进行赋值
     */
    public void setTreePath(String treePath)
    {
        this.treePath = treePath;
    }
    
    /**
     * @return 返回 grade
     */
    @Column(nullable = false)
    public Integer getGrade()
    {
        return grade;
    }
    
    /**
     * @param 对grade进行赋值
     */
    public void setGrade(Integer grade)
    {
        this.grade = grade;
    }
    
    /**
     * @param 对parent进行赋值
     */
    public void setParent(Navigation parent)
    {
        this.parent = parent;
    }
    
    /**
     * @return 返回 children
     */
    @OneToMany(mappedBy = "parent")
    @OrderBy("order asc")
    public Set<Navigation> getChildren()
    {
        return children;
    }
    
    /**
     * @param 对children进行赋值
     */
    public void setChildren(Set<Navigation> children)
    {
        this.children = children;
    }
    
    /**
     * @return
     */
    @Override
    public String toString()
    {
        return "Navigation [name=" + name + ", position=" + position + ", url="
                + url + ", isBlankTarget=" + isBlankTarget + ", parent="
                + parent + ", children=" + children + "]";
    }
    
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
     * 获取位置
     * 
     * @return 位置
     */
    @NotNull
    @Column(nullable = false)
    public Position getPosition()
    {
        return position;
    }
    
    /**
     * 设置位置
     * 
     * @param position
     *            位置
     */
    public void setPosition(Position position)
    {
        this.position = position;
    }
    
    /**
     * 获取链接地址
     * 
     * @return 链接地址
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getUrl()
    {
        return url;
    }
    
    /**
     * 设置链接地址
     * 
     * @param url
     *            链接地址
     */
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    /**
     * 获取是否新窗口打开
     * 
     * @return 是否新窗口打开
     */
    @NotNull
    @Column(nullable = false)
    public Boolean getIsBlankTarget()
    {
        return isBlankTarget;
    }
    
    /**
     * 设置是否新窗口打开
     * 
     * @param isBlankTarget
     *            是否新窗口打开
     */
    public void setIsBlankTarget(Boolean isBlankTarget)
    {
        this.isBlankTarget = isBlankTarget;
    }
    /**
     * 获取树路径
     * 
     * @return 树路径
     */
    @Transient
    public List<Long> getTreePaths()
    {
        List<Long> treePaths = new ArrayList<Long>();
        String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
        if (ids != null)
        {
            for (String id : ids)
            {
                treePaths.add(Long.valueOf(id));
            }
        }
        return treePaths;
    }
}