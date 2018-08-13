package com.hxc.cms.service.news.impl;

import com.hxc.cms.dao.NewsRepository;
import com.hxc.cms.enums.Mark;
import com.hxc.cms.enums.NewsStatus;
import com.hxc.cms.model.News;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.news.NewsService;
import com.hxc.cms.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    
    @Override
    public Page<News> findNewsByPage(News newsParam, PageParam pageParam) {
        Example<News> example = Example.of(newsParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime","updateTime")
                // 忽略大小写。
                .withIgnoreCase()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.startsWith()) //姓名采用“开始匹配”的方式查询
                .withMatcher("sources", ExampleMatcher.GenericPropertyMatchers.startsWith()) //姓名采用“开始匹配”的方式查询
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(newsParam, exampleMatcher);
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows(),Sort.Direction.DESC,"createTime"); //页码：前端从1开始，jpa从0开始，做个转换
        return this.newsRepository.findAll(example,pageable);
    }
    
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
        if(!ObjectUtil.isNotBlank(news.getId())){
            if(!ObjectUtil.isNotBlank(news.getStatus())){
                news.setStatus(NewsStatus.EDIT.getCode());
            }
            if(!ObjectUtil.isNotBlank(news.getIsTop())){
                news.setIsTop(Mark.NO.getCode());
            }
            news.setCreateTime(new Date());
            news.setUpdateTime(new Date());
            this.newsRepository.save(news);
        }else{
            News _news = this.newsRepository.getOne(news.getId());
            if(_news != null){
                _news.setTitle(news.getTitle());
                _news.setSubTitle(news.getSubTitle());
                _news.setIsOriginal(news.getIsOriginal());
                _news.setIsTop(news.getIsTop());
                _news.setPic(news.getPic());
                _news.setIntroduction(news.getIntroduction());
                _news.setContext(news.getContext());
                _news.setSources(news.getSources());
                _news.setStatus(news.getStatus());
                _news.setNewCategoryId(news.getNewCategoryId());
                _news.setSourUrl(news.getSourUrl());
                this.newsRepository.save(_news);
            }
        }
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
