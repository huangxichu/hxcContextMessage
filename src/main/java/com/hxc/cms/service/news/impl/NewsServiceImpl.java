package com.hxc.cms.service.news.impl;

import com.hxc.cms.dao.NewsRepository;
import com.hxc.cms.model.News;
import com.hxc.cms.service.news.NewsService;
import com.hxc.cms.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> findNews(News newsParam) {
        Example<News> example = Example.of(newsParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime","updateTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(newsParam, exampleMatcher);
        return this.newsRepository.findAll(example);
    }

    @Override
    @Transactional
    public void save(News news) {
        this.newsRepository.save(news);
    }

    @Override
    @Transactional
    public void update(News news) {
        this.newsRepository.save(news);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if(ObjectUtil.isNotBlank(id)){
            this.newsRepository.delete(id);
        }
    }

    @Override
    @Transactional
    public void deletes(List<Integer> ids) {
        if(ObjectUtil.isNotBlank(ids)){
            this.newsRepository.deleteByIds(ids);
        }
    }
}
