/**
 * 文   件  名:  VideoDaoImpl.java
 * 描          述：<功能模块名称>
 * 修   改  人:  Administrator
 * 修改时间:  2016年1月6日
 *         <修改描述:>
 * @author Administrator
 * @version [1.0 2016年1月6日]
 * Copyright: Copyright (c) LCZXTECH Co.,Ltd. 2015
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
package com.lczx.dao.impl;

import org.springframework.stereotype.Repository;

import com.lczx.dao.VideoDao;
import com.lczx.entity.Video;



/**
 * dao - 原创视频
 */
@Repository("videoDaoImpl")
public class VideoDaoImpl extends BaseDaoImpl<Video, Long> implements VideoDao
{
    
}
