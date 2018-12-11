/*



 */
package com.lczx.service;

import java.util.List;

import com.lczx.entity.Area;


/**
 * 项目名称：
 * 功能模块名称：Service - 地区
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface AreaService extends BaseService<Area, Long>
{
    
    /**
     * 查找顶级地区
     * 
     * @return 顶级地区
     */
    List<Area> findRoots();
    
    /**
     * 查找顶级地区
     * 
     * @param count
     *            数量
     * @return 顶级地区
     */
    List<Area> findRoots(Integer count);
    
}