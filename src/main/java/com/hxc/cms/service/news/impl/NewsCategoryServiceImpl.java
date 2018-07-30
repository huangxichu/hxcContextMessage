package com.hxc.cms.service.news.impl;

import com.hxc.cms.dao.NewsCategoryRepository;
import com.hxc.cms.service.news.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("newsCategoryService")
public class NewsCategoryServiceImpl implements NewsCategoryService {

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;



}
