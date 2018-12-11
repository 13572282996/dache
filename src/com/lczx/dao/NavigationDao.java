/*



 */
package com.lczx.dao;

import java.util.List;

import com.lczx.entity.Navigation;
import com.lczx.entity.Navigation.Position;


/**
 * 项目名称：
 * 功能模块名称：Dao - 导航
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface NavigationDao extends BaseDao<Navigation, Long>
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
     * 查找下级导航
     * 
     * @param articleCategory
     *            文章分类
     * @param count
     *            数量
     * @return 下级文章分类
     */
    List<Navigation> findChildren(Navigation navigation, Integer count);
    
    List<Navigation> findParentByPosition(Position position);
    
}