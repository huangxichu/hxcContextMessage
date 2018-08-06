package com.hxc.cms.service.message;

import com.hxc.cms.model.Message;
import com.hxc.cms.param.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {
    
    Page<Message> findMessagesByPage(Message messageParam, PageParam pageParam);
    
    List<Message> findMessages(Message messageParam);

    void save(Message message);

    void update(Message message);

    void delete(Integer id);

    void deletes(List<Integer> ids);

}
