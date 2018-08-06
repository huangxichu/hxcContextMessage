package com.hxc.cms.service.message.impl;

import com.hxc.cms.dao.MessageRepository;
import com.hxc.cms.model.Message;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.message.MessageService;
import com.hxc.cms.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    
    @Override
    public Page<Message> findMessagesByPage(Message messageParam, PageParam pageParam) {
        Example<Message> example = Example.of(messageParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(messageParam, exampleMatcher);
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows()); //页码：前端从1开始，jpa从0开始，做个转换
        return this.messageRepository.findAll(example,pageable);
    }
    
    @Override
    public List<Message> findMessages(Message messageParam) {
        Example<Message> example = Example.of(messageParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(messageParam, exampleMatcher);
        return this.messageRepository.findAll(example);
    }

    @Override
    @Transactional
    public void save(Message message) {
        this.messageRepository.save(message);
    }

    @Override
    @Transactional
    public void update(Message message) {
        this.messageRepository.save(message);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if(ObjectUtil.isNotBlank(id)){
            this.messageRepository.delete(id);
        }
    }

    @Override
    @Transactional
    public void deletes(List<Integer> ids) {
        if(ObjectUtil.isNotBlank(ids)){
            this.messageRepository.deleteByIds(ids);
        }
    }
}
