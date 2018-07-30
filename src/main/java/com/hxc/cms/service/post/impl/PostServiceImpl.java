package com.hxc.cms.service.post.impl;

import com.hxc.cms.dao.PostRepository;
import com.hxc.cms.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;


}
