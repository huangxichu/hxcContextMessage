package com.hxc.cms.service.post;

import com.hxc.cms.model.Post;
import com.hxc.cms.param.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    
    Page<Post> findPostsByPage(Post postParam, PageParam pageParam);
    
    List<Post> findPostes(Post postParam);
    
    void save(Post post);
    
    void update(Post post);
    
    void delete(Integer id);
    
    void deletes(List<Integer> ids);
    
    
}
