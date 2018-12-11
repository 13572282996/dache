/*



 */
package com.lczx.entity;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dom4j.io.SAXReader;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jsoup.Jsoup;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lczx.freeMarker.FreeMarkerViewUtil;
import com.lczx.util.CommonAttributes;

import freemarker.template.TemplateException;

/**
 * 项目名称：
 * 功能模块名称：Entity - 文章
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Indexed
@Entity
@Table(name = "cms_article")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "cms_article_sequence")
public class Article extends BaseEntity
{
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 797590831803228447L;

    /** 点击数缓存名称 */
    public static final String HITS_CACHE_NAME = "articleHits";
    
    /** 点击数缓存更新间隔时间 */
    public static final int HITS_CACHE_INTERVAL = 600000;
    
    /** 内容分页符 */
    
    /** 静态路径 */
    private static String staticPath;
    
    /** 标题 */
    private String title;
    
    /** 作者 */
    private String author;
    
    /** 内容 */
    private String content;
    
    /** 页面标题 */
    private String seoTitle;
    
    /** 页面关键词 */
    private String seoKeywords;
    
    /** 页面描述 */
    private String seoDescription;
    
    /** 是否发布 */
    private Boolean isPublication;
    
    /** 文章来源 */
    private String articleForm;
    
    /** 是否置顶 */
    private Boolean isTop;
    
    /** 点击数 */
    private Long hits;
    
    /** 文章分类 */
    private ArticleCategory articleCategory;
    
    /** 简介*/
    private String info;
    
    /** 展示图片 */
    private String picture;
    
    static
    {
        try
        {
            File definedweekXmlFile = new ClassPathResource(
                    CommonAttributes.DEFINEDWEEK_XML_PATH).getFile();
            org.dom4j.Document document = new SAXReader().read(definedweekXmlFile);
            org.dom4j.Element element = (org.dom4j.Element) document.selectSingleNode("/definedweek/template[@id='articleContent']");
            staticPath = element.attributeValue("staticPath");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
    /**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the info
	 */
    @Length(max = 200)
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
     * 获取标题
     * 
     * @return 标题
     */
    @NotEmpty
    @JsonProperty
    @Length(max = 200)
    @Column(nullable = false)
    public String getTitle()
    {
        return title;
    }
    
    /**
     * 设置标题
     * 
     * @param title
     *            标题
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * 获取作者
     * 
     * @return 作者
     */
    @JsonProperty
    @Field(store = Store.YES, index = Index.NO)
    @Length(max = 200)
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * 设置作者
     * 
     * @param author
     *            作者
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    /**
     * 获取内容
     * 
     * @return 内容
     */
    @JsonProperty
    @Lob
    public String getContent()
    {
        
        return content;
    }
    
    /**
     * 设置内容
     * 
     * @param content
     *            内容
     */
    public void setContent(String content)
    {
        this.content = content;
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
        if (seoKeywords != null)
        {
            seoKeywords = seoKeywords.replaceAll("[,\\s]*,[,\\s]*", ",")
                    .replaceAll("^,|,$", "");
        }
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
     * 获取是否发布
     * 
     * @return 是否发布
     */
    @Field(store = Store.YES)
    @NotNull
    @Column(nullable = false)
    public Boolean getIsPublication()
    {
        return isPublication;
    }
    
    /**
     * 设置是否发布
     * 
     * @param isPublication
     *            是否发布
     */
    public void setIsPublication(Boolean isPublication)
    {
        this.isPublication = isPublication;
    }
    
    /**
     * 获取是否置顶
     * 
     * @return 是否置顶
     */
    @Field(store = Store.YES)
    @NotNull
    @Column(nullable = false)
    public Boolean getIsTop()
    {
        return isTop;
    }
    
    /**
     * 设置是否置顶
     * 
     * @param isTop
     *            是否置顶
     */
    public void setIsTop(Boolean isTop)
    {
        this.isTop = isTop;
    }
    
    /**
     * 获取点击数
     * 
     * @return 点击数
     */
    @Column(nullable = false)
    public Long getHits()
    {
        return hits;
    }
    
    /**
     * 设置点击数
     * 
     * @param hits
     *            点击数
     */
    public void setHits(Long hits)
    {
        this.hits = hits;
    }
    
    
    /**
     * 获取文章来源
     * @return 返回 articleForm
     */
    public String getArticleForm()
    {
        return articleForm;
    }
    
    /**
     * 设置文章来源
     * @param 对articleForm进行赋值
     */
    public void setArticleForm(String articleForm)
    {
        this.articleForm = articleForm;
    }
    
    
   
    
    /**
     * 获取文章分类
     * 
     * @return 文章分类
     */
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    public ArticleCategory getArticleCategory()
    {
        return articleCategory;
    }
    
    /**
     * 设置文章分类
     * 
     * @param articleCategory
     *            文章分类
     */
    public void setArticleCategory(ArticleCategory articleCategory)
    {
        this.articleCategory = articleCategory;
    }
    
    /**
     * 获取访问路径
     * 
     * @return 访问路径
     */
    @Transient
    public String getPath()
    {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", getId());
        model.put("createDate", getCreateDate());
        model.put("modifyDate", getModifyDate());
        model.put("title", getTitle());
        model.put("seoTitle", getSeoTitle());
        model.put("seoKeywords", getSeoKeywords());
        model.put("seoDescription", getSeoDescription());
        model.put("articleCategory", getArticleCategory());
        try
        {
            return FreeMarkerViewUtil.process(staticPath, model);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取文本内容
     * 
     * @return 文本内容
     */
    @Transient
    public String getText()
    {
        if (getContent() != null)
        {
            return Jsoup.parse(getContent()).text();
        }
        return null;
    }
    
}