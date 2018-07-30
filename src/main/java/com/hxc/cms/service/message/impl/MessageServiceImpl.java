package com.hxc.cms.service.message.impl;

import com.hxc.cms.dao.MessageRepository;
import com.hxc.cms.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

}
