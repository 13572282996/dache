/**
 * 文   件  名:  VideoServiceImpl.java
 * 描          述：<功能模块名称>
 * 修   改  人:  Administrator
 * 修改时间:  2016年1月6日
 *         <修改描述:>
 * @author Administrator
 * @version [1.0 2016年1月6日]
 * Copyright: Copyright (c) LCZXTECH Co.,Ltd. 2015
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.VideoDao;
import com.lczx.entity.Video;
import com.lczx.service.VideoService;


/**
 *service - 原创视频
 */
@Service("videoServiceImpl")
public class VideoServiceImpl extends BaseServiceImpl<Video, Long> implements VideoService
{
    @Resource(name = "videoDaoImpl")
    private VideoDao videoDao;

    @Resource(name = "videoDaoImpl")
    public void setBaseDao(VideoDao videoDao) {
        super.setBaseDao(videoDao);
    }
}
