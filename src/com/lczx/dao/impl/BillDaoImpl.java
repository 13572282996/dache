package com.lczx.dao.impl;

import org.springframework.stereotype.Repository;

import com.lczx.dao.BillDao;
import com.lczx.entity.Bill;
/**
 * 发票 
 * 
 **/
@Repository("billDaoImpl")
public class BillDaoImpl extends BaseDaoImpl<Bill, Long> implements BillDao
{
    
}
