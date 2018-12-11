/*



 */
package com.lczx.service;

import java.util.List;

import com.lczx.entity.Navigation;
import com.lczx.entity.Navigation.Position;
import com.lczx.util.Filter;
import com.lczx.util.Order;



/**
 * 项目名称：
 * 功能模块名称：Service - 导航
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface NavigationService extends BaseService<Navigation, Long>
{
    
    /**
     * 查找导航
     * 
     * @param position
     *            位置
     * @return 导航
     */
    List<Navigation> findList(Position position);
    
    /**
     * 查找导航(缓存)
     * 
     * @param count
     *            数量
     * @param filters
     *            筛选
     * @param orders
     *            排序
     * @param cacheRegion
     *            缓存区域
     * @return 导航(缓存)
     */
    List<Navigation> findList(Integer count, List<Filter> filters,
            List<Order> orders, String cacheRegion);
    
    /**
     * 查找导航树
     * 
     * @return 导航树树
     */
    List<Navigation> findTree();
    
    /**
     * 查找下级导航
     * 
     * @param articleCategory
     *            导航
     * @param count
     *            数量
     * @return 下级导航
     */
    List<Navigation> findChildren(Navigation navigation, Integer count);
    
    /** 
     * <功能简述>
     * <功能详细描述>
     * @param navigation
     * @return [参数说明]
     * 
     * @return List<Navigation> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    List<Navigation> findChildren(Navigation navigation);
    
    /**
        * 查找导航树
        * 
        * @return 导航树树
        */
    List<Navigation> findTree(Position top);
    
}