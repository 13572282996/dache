package com.lczx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.MessageDao;
import com.lczx.entity.Message;
import com.lczx.service.MessageService;

@Service("messageServiceImpl")
public class MessageServiceImpl extends BaseServiceImpl<Message, Long>
        implements MessageService
{
    @Resource(name = "messageDaoImpl")
    private MessageDao messageDao;
    
    @Resource(name = "messageDaoImpl")
    public void setBaseDao(MessageDao messageDao)
    {
        super.setBaseDao(messageDao);
    }
}
