/**
 * 文   件  名:  ReferenceService.java
 * 描          述：<功能模块名称>
 * 修   改  人:  Administrator
 * 修改时间:  2015年12月10日
 *         <修改描述:>
 * @author Administrator
 * @version [1.0 2015年12月10日]
 * Copyright: Copyright (c) LCZXTECH Co.,Ltd. 2015
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
package com.lczx.service;

import com.lczx.entity.Reference;


/**
 * service-备考专区
 */
public interface ReferenceService extends BaseService<Reference, Long>
{
    /**
     * 根据备考专区分类查找备考专区
     * 
     * @param mobile
     *            备考专区分类(忽略大小写)
     * @return 备考专区，若不存在则返回null
     */
    Reference findByReferenceClass(String referenceClass);
}
