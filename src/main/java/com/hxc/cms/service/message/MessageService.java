package com.hxc.cms.service.message;

import com.hxc.cms.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> findMessages(Message messageParam);

    void save(Message message);

    void update(Message message);

    void delete(Integer id);

    void deletes(List<Integer> ids);

}
