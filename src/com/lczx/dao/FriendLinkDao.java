/*



 */
package com.lczx.dao;

import java.util.List;

import com.lczx.entity.FriendLink;
import com.lczx.entity.FriendLink.Type;


/**
 * 项目名称：
 * 功能模块名称：Dao - 友情链接
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface FriendLinkDao extends BaseDao<FriendLink, Long>
{
    
    /**
     * 查找友情链接
     * 
     * @param type
     *            类型
     * @return 友情链接
     */
    List<FriendLink> findList(Type type);
    
}