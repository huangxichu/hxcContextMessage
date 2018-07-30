package com.hxc.cms.service.news.impl;

import com.hxc.cms.dao.NewsRepository;
import com.hxc.cms.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

}
