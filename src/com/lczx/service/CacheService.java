/*



 */
package com.lczx.service;

/**
 * 项目名称：
 * 功能模块名称：Service - 缓存
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface CacheService
{
    
    /**
     * 获取缓存存储路径
     * 
     * @return 缓存存储路径
     */
    String getDiskStorePath();
    
    /**
     * 获取缓存数
     * 
     * @return 缓存数
     */
    int getCacheSize();
    
    /**
     * 清除缓存
     */
    void clear();
    
}