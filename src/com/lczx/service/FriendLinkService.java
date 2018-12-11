/*



 */
package com.lczx.service;

import java.util.List;

import com.lczx.entity.FriendLink;
import com.lczx.entity.FriendLink.Type;
import com.lczx.util.Filter;
import com.lczx.util.Order;



/**
 * 项目名称：
 * 功能模块名称：Service - 友情链接
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface FriendLinkService extends BaseService<FriendLink, Long>
{
    
    /**
     * 查找友情链接
     * 
     * @param type
     *            类型
     * @return 友情链接
     */
    List<FriendLink> findList(Type type);
    
    /**
     * 查找友情链接(缓存)
     * 
     * @param count
     *            数量
     * @param filters
     *            筛选
     * @param orders
     *            排序
     * @param cacheRegion
     *            缓存区域
     * @return 友情链接(缓存)
     */
    List<FriendLink> findList(Integer count, List<Filter> filters,
            List<Order> orders, String cacheRegion);
    
}