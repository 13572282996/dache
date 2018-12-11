/**
 * 文 件 名:  ResourceService.java
 * 版       权:  Copyright (c) ZenSoftware Co.,Ltd. 2014
 * 描       述: 
 * 修  改   人:yideng
 * 修改时间:Oct 28, 2014
 * 修改描述:
 */
package com.lczx.service;

import java.util.List;

import com.lczx.entity.Resource;


/**
 * 项目名称：资源权限管理
 * 功能模块名称：
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Oct 28, 2014
 * Copyright: Copyright (c) ZenSoftware Co.,Ltd. 2014
 * Company:四川天思科技有限责任公司 www.zensoftware.com.cn
 */

public interface ResourceService extends BaseService<Resource, Long>
{
    List<Resource> findResourceGroup();
    
    /** 
     * 功能简述：
     * 功能详细描述：
     * @author ZHAOBING
     * @param previousName
     * @param currentName
     * @return [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    boolean nameUnique(String previousName, String currentName);
    
    /** 
     * 功能简述：
     * 功能详细描述：
     * @author ZHAOBING
     * @param previousMark
     * @param currentMark
     * @return [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    boolean markUnique(String previousMark, String currentMark);
    
    /** 
     * 功能简述：
     * 功能详细描述：
     * @author ZHAOBING
     * @param previousUrl
     * @param currentUrl
     * @return [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    boolean resourceUrlUnique(String previousUrl, String currentUrl);
}
