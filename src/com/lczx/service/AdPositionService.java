/*



 */
package com.lczx.service;

import com.lczx.entity.AdPosition;


/**
 * 项目名称：
 * 功能模块名称：Service - 广告位
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface AdPositionService extends BaseService<AdPosition, Long>
{
    
    /**
     * 查找广告位(缓存)
     * 
     * @param id
     *            ID
     * @param cacheRegion
     *            缓存区域
     * @return 广告位(缓存)
     */
    AdPosition find(Long id, String cacheRegion);
    
}