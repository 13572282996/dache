/**
 * 文   件  名:  reference.java
 * 描          述：<功能模块名称>
 * 修   改  人:  Administrator
 * 修改时间:  2015年12月10日
 *         <修改描述:>
 * @author Administrator
 * @version [1.0 2015年12月10日]
 * Copyright: Copyright (c) LCZXTECH Co.,Ltd. 2015
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
package com.lczx.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * entity - 文档管理
 */
@Entity
@Table(name = "cms_reference")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "cms_reference_sequence")
public class Reference extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2466495403081723647L;
    
    /**文档封面*/
    private String  referenceClass;
    
    /** 文件名*/
    private String fileName;
    
    /**文件保存路径*/
    private String filePath;
    
    /** 所属分类 */
    private ArticleCategory articleCategory;
    
    /**下载量*/
    private int dloaded;

    /**
     * 获取备考专区分类
     * @return 备考专区分类
     */
    public String getReferenceClass()
    {
        return referenceClass;
    }

    /**
     * 设置备考专区分类
     * @param 对referenceClass进行赋值
     *                      备考专区分类
     */
    public void setReferenceClass(String referenceClass)
    {
        this.referenceClass = referenceClass;
    }

    /**
     * 获取文件名
     * @return 文件名
     */
    @NotEmpty
    public String getFileName()
    {
        return fileName;
    }

    /**设置文件名
     * @param 对fileName进行赋值
     *                  文件名
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * 获取原文件保存路径
     * @return 返回 filePath
     */
    public String getFilePath()
    {
        return filePath;
    }

    /**
     * 设置 原文件保存路径
     * @param 对filePath进行赋值
     *               原文件保存路径
     */
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    /**
     * 获取下载量
     * @return 下载量
     */
    public int getDloaded()
    {
        return dloaded;
    }

    /**
     * 设置下载量
     * @param 对dloaded进行赋值
     *              下载量
     */
    public void setDloaded(int dloaded)
    {
        this.dloaded = dloaded;
    }

    /**
     * 获取文章分类
     * @return 返回 articleCategory
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public ArticleCategory getArticleCategory()
    {
        return articleCategory;
    }

    /**
     * 设置文章分类
     * @param 对articleCategory进行赋值
     */
    public void setArticleCategory(ArticleCategory articleCategory)
    {
        this.articleCategory = articleCategory;
    }
    
    
}
