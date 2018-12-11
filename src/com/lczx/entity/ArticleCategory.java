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

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 项目名称：
 * 功能模块名称：Entity - 文章分类
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Entity
@Table(name = "cms_article_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "cms_article_category_sequence")
public class ArticleCategory extends OrderEntity
{
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -4624466013991916559L;

    /** 树路径分隔符 */
    public static final String TREE_PATH_SEPARATOR = ",";
    
  
    
    /** 访问路径前缀 */
    private String beforePath;
    
    /** 名称 */
    private String name;
    
    private String enName;
    
    /** 页面标题 */
    private String seoTitle;
    
    /** 页面关键词 */
    private String seoKeywords;
    
    /** 页面描述 */
    private String seoDescription;
    
    /** 树路径 */
    private String treePath;
    
    /** 层级 */
    private Integer grade;
    
    /** 上级分类 */
    private ArticleCategory parent;
    
    /** 下级分类 */
    private Set<ArticleCategory> children = new HashSet<ArticleCategory>();
    
    /** 文章 */
    private Set<Article> articles = new HashSet<Article>();
    
    /**视屏类*/
    private Set<Video> videos = new HashSet<Video>();
    
    private Set<Reference> references = new HashSet<Reference>();
    
    
    
    /**
	 * @return the enName
	 */
	public String getEnName() {
		return enName;
	}

	/**
	 * @param enName the enName to set
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}

	/**
     * 获取访问路径前
     * @return 返回 beforePath
     */
    public String getBeforePath()
    {
        return beforePath;
    }

    /**
     * 设置访问路径前缀
     * @param 对beforePath进行赋值
     */
    public void setBeforePath(String beforePath)
    {
        this.beforePath = beforePath;
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
     * 获取页面标题
     * 
     * @return 页面标题
     */
    @Length(max = 200)
    public String getSeoTitle()
    {
        return seoTitle;
    }
    
    /**
     * 设置页面标题
     * 
     * @param seoTitle
     *            页面标题
     */
    public void setSeoTitle(String seoTitle)
    {
        this.seoTitle = seoTitle;
    }
    
    /**
     * 获取页面关键词
     * 
     * @return 页面关键词
     */
    @Length(max = 200)
    public String getSeoKeywords()
    {
        return seoKeywords;
    }
    
    /**
     * 设置页面关键词
     * 
     * @param seoKeywords
     *            页面关键词
     */
    public void setSeoKeywords(String seoKeywords)
    {
        this.seoKeywords = seoKeywords;
    }
    
    /**
     * 获取页面描述
     * 
     * @return 页面描述
     */
    @Length(max = 200)
    public String getSeoDescription()
    {
        return seoDescription;
    }
    
    /**
     * 设置页面描述
     * 
     * @param seoDescription
     *            页面描述
     */
    public void setSeoDescription(String seoDescription)
    {
        this.seoDescription = seoDescription;
    }
    
    /**
     * 获取树路径
     * 
     * @return 树路径
     */
    @Column(nullable = false)
    public String getTreePath()
    {
        return treePath;
    }
    
    /**
     * 设置树路径
     * 
     * @param treePath
     *            树路径
     */
    public void setTreePath(String treePath)
    {
        this.treePath = treePath;
    }
    
    /**
     * 获取层级
     * 
     * @return 层级
     */
    @Column(nullable = false)
    public Integer getGrade()
    {
        return grade;
    }
    
    /**
     * 设置层级
     * 
     * @param grade
     *            层级
     */
    public void setGrade(Integer grade)
    {
        this.grade = grade;
    }
    
    /**
     * 获取上级分类
     * 
     * @return 上级分类
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public ArticleCategory getParent()
    {
        return parent;
    }
    
    /**
     * 设置上级分类
     * 
     * @param parent
     *            上级分类
     */
    public void setParent(ArticleCategory parent)
    {
        this.parent = parent;
    }
    
    /**
     * 获取下级分类
     * 
     * @return 下级分类
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @OrderBy("order asc")
    public Set<ArticleCategory> getChildren()
    {
        return children;
    }
    
    /**
     * 设置下级分类
     * 
     * @param children
     *            下级分类
     */
    public void setChildren(Set<ArticleCategory> children)
    {
        this.children = children;
    }
    
    /**
     * 获取文章
     * 
     * @return 文章
     */
    @OneToMany(mappedBy = "articleCategory", fetch = FetchType.LAZY)
    public Set<Article> getArticles()
    {
        return articles;
    }
    
    /**
     * 设置文章
     * 
     * @param articles
     *            文章
     */
    public void setArticles(Set<Article> articles)
    {
        this.articles = articles;
    }
    
    @OneToMany(mappedBy = "articleCategory", fetch = FetchType.LAZY)
    public Set<Video> getVideos()
    {
        return videos;
    }

    public void setVideos(Set<Video> videos)
    {
        this.videos = videos;
    }
    
    /**
     * 获取文档管理
     * @return 返回 references
     */
    @OneToMany(mappedBy = "articleCategory", fetch = FetchType.LAZY)
    public Set<Reference> getReferences()
    {
        return references;
    }

    /**
     * 设置文档管理
     * @param 对references进行赋值
     */
    public void setReferences(Set<Reference> references)
    {
        this.references = references;
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
    
    /**
     * 获取访问路径
     * 
     * @return 访问路径
     */
    @Transient
    public String getPath()
    {
        if (getId() != null)
        {
            return getBeforePath();
        }
        return null;
    }
    
    /**
     * 获取树路径中顶级id
     * 
     * @return 树路径中顶级id
     */
    @Transient
    public Long getTreePathFirstId(ArticleCategory articleCategory)
    {
    	if (articleCategory.getParent() == null) 
    	{
			return articleCategory.getId();
		}
    	else
    	{
			String treePath2 = articleCategory.getTreePath();
			String[] array = treePath2.split(",");
			Long id = Long.parseLong(array[1]);
			return id;
		}
    }
    
}