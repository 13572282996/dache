/**
 * 文   件  名:  ReferenceServiceImpl.java
 * 描          述：<功能模块名称>
 * 修   改  人:  Administrator
 * 修改时间:  2015年12月10日
 *         <修改描述:>
 * @author Administrator
 * @version [1.0 2015年12月10日]
 * Copyright: Copyright (c) LCZXTECH Co.,Ltd. 2015
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lczx.dao.ReferenceDao;
import com.lczx.entity.Reference;
import com.lczx.service.ReferenceService;


/**
 * service-备考专区
 */
@Service("referenceServiceImpl")
public class ReferenceServiceImpl extends BaseServiceImpl<Reference, Long> implements ReferenceService
{
    @Resource(name="referenceDaoImpl")
    private ReferenceDao referenceDao;

  
    @Resource(name="referenceDaoImpl")
    public void setBaseDao(ReferenceDao referenceDao)
    {
        super.setBaseDao(referenceDao);
    }
    
    @Transactional(readOnly = true)
    public Reference findByReferenceClass(String referenceClass) {
            return referenceDao.findByReferenceClass(referenceClass);
        }
}
