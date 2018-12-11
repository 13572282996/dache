package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.BillDao;
import com.lczx.entity.Bill;
import com.lczx.service.BillService;

@Service("billServiceImpl")
public class BillServiceImpl extends BaseServiceImpl<Bill, Long> implements
        BillService
{
    @Resource(name = "billDaoImpl")
    private BillDao billDao;
    
    @Resource(name = "billDaoImpl")
    public void setBaseDao(BillDao billDao)
    {
        super.setBaseDao(billDao);
    }
    
}
