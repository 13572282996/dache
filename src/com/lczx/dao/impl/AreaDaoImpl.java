/*



 */
package com.lczx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lczx.dao.AreaDao;
import com.lczx.entity.Area;



/**
 * 项目名称：
 * 功能模块名称：Dao - 地区
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Repository("areaDaoImpl")
public class AreaDaoImpl extends BaseDaoImpl<Area, Long> implements AreaDao
{
    
    public List<Area> findRoots(Integer count)
    {
        String jpql = "select area from Area area where area.parent is null order by area.order asc";
        TypedQuery<Area> query = entityManager.createQuery(jpql, Area.class)
                .setFlushMode(FlushModeType.COMMIT);
        if (count != null)
        {
            query.setMaxResults(count);
        }
        return query.getResultList();
    }
    
}