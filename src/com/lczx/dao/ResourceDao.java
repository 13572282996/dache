/**
 * 文 件 名:  ResourceDao.java
 * 版       权:  Copyright (c) ZenSoftware Co.,Ltd. 2014
 * 描       述: 
 * 修  改   人:yideng
 * 修改时间:Oct 28, 2014
 * 修改描述:
 */
package com.lczx.dao;

import java.util.List;

import com.lczx.entity.Resource;


/**
 * 项目名称：
 * 功能模块名称：
 * 功能描述：
 * @author yideng
 * @version 1.0 Oct 28, 2014
 * Copyright: Copyright (c) ZenSoftware Co.,Ltd. 2014
 * Company:四川天思科技有限责任公司 www.zensoftware.com.cn
 */

public interface ResourceDao extends BaseDao<Resource, Long>
{
    
    /** 
     * 功能简述：
     * 功能详细描述：
     * @author yideng
     * @return [参数说明]
     * @return List<Resource> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    List<Resource> findResourceGroup();
    
    /** 
     * 功能简述：
     * 功能详细描述：
     * @author yideng
     * @param name
     * @return [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    boolean nameExists(String name);
    
    /** 
     * 功能简述：
     * 功能详细描述：
     * @author yideng
     * @param mark
     * @return [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    boolean markExists(String mark);
    
    /** 
     * 功能简述：
     * 功能详细描述：
     * @author yideng
     * @param url
     * @return [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    boolean resourceUrlExists(String url);
    
}
