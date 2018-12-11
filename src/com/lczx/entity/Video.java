/**
 * 文   件  名:  Video.java
 * 描          述：<功能模块名称>
 * 修   改  人:  Administrator
 * 修改时间:  2016年1月6日
 *         <修改描述:>
 * @author Administrator
 * @version [1.0 2016年1月6日]
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

/**
 * entity - 原创视屏
 */
@Entity
@Table(name = "cms_video")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "cms_video_sequence")
public class Video extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -9163902775891289663L;
    
    /** 视频名*/
    private String videoName;
    
    /** 视频介绍*/
    private String videoSaid;
    
    /** 视频存储路径*/
    private String videoPath;
    
    /** 视频截图*/
    private String videoPicture;
    
    /** 所属分类 */
    private ArticleCategory articleCategory;

    /**
     * 获取视频名
     * @return 返回 videoName
     */
    public String getVideoName()
    {
        return videoName;
    }

    /**
     * 设置视频名
     * @param 对videoName进行赋值
     *              视频名
     */
    public void setVideoName(String videoName)
    {
        this.videoName = videoName;
    }

    /**
     * 获得视频介绍
     * @return 返回 videoSaid
     */
    public String getVideoSaid()
    {
        return videoSaid;
    }

    /**
     * 设置视频介绍
     * @param 对videoSaid进行赋值
     *              视频介绍
     */
    public void setVideoSaid(String videoSaid)
    {
        this.videoSaid = videoSaid;
    }

    /**
     * 获得视频存储路径
     * @return 返回 videoPath
     */
    public String getVideoPath()
    {
        return videoPath;
    }

    /**
     * 设置视频存储路径
     * @param 对videoPath进行赋值
     *                  视频存储路径
     */
    public void setVideoPath(String videoPath)
    {
        this.videoPath = videoPath;
    }

    /**
     * 获得视频截图
     * @return 返回 videoPicture
     */
    public String getVideoPicture()
    {
        return videoPicture;
    }

    /**
     * 设置视频截图
     * @param 对videoPicture进行赋值
     *                  视频截图
     */
    public void setVideoPicture(String videoPicture)
    {
        this.videoPicture = videoPicture;
    }
    
    /**
     * 获取文章分类
     * 
     * @return 文章分类
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
     * 
     * @param articleCategory
     *            文章分类
     */
    public void setArticleCategory(ArticleCategory articleCategory)
    {
        this.articleCategory = articleCategory;
    }
    
}
