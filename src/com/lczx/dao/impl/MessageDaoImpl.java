package com.lczx.dao.impl;

import org.springframework.stereotype.Repository;

import com.lczx.dao.MessageDao;
import com.lczx.entity.Message;

@Repository("messageDaoImpl")
public class MessageDaoImpl extends BaseDaoImpl<Message, Long> implements
        MessageDao
{
    
}
