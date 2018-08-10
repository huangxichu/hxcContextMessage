package com.hxc.cms.service.post.impl;

import com.hxc.cms.dao.PostRepository;
import com.hxc.cms.enums.Status;
import com.hxc.cms.model.Department;
import com.hxc.cms.model.Post;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.post.PostService;
import com.hxc.cms.utils.ObjectUtil;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    
    
    @Override
    public Page<Post> findPostsByPage(Post postParam, PageParam pageParam) {
        Example<Post> example = Example.of(postParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime")
                // 忽略大小写。
                .withIgnoreCase()
                .withMatcher("postName", ExampleMatcher.GenericPropertyMatchers.startsWith()) //采用“开始匹配”的方式查询
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(postParam, exampleMatcher);
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows(),Sort.Direction.DESC,"createTime"); //页码：前端从1开始，jpa从0开始，做个转换
        return this.postRepository.findAll(example,pageable);
    }
    
    @Override
    public List<Post> findPostes(Post postParam) {
        Example<Post> example = Example.of(postParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(postParam, exampleMatcher);
        return this.postRepository.findAll(example);
    }
    
    @Override
    @Transactional
    public void save(Post post) {
        if(!ObjectUtil.isNotBlank(post.getId())){
            if(!ObjectUtil.isNotBlank(post.getStatus())){
                post.setStatus(Status.ENABLE.getCode());
            }
            post.setCreateTime(new Date());
            this.postRepository.save(post);
        }else{
            Post _old = this.postRepository.getOne(post.getId());
            if(_old != null){
                _old.setStatus(post.getStatus());
                _old.setPostName(post.getPostName());
                this.postRepository.save(_old);
            }
        }
    }
    
    @Override
    @Transactional
    public void update(Post post) {
        this.postRepository.save(post);
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if(ObjectUtil.isNotBlank(id)){
            this.postRepository.delete(id);
        }
    }
    
    @Override
    @Transactional
    public void deletes(List<Integer> ids) {
        if(ObjectUtil.isNotBlank(ids)){
            this.postRepository.deleteByIds(ids);
        }
    }
}
