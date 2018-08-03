package com.hxc.cms.service.news.impl;

import com.hxc.cms.dao.NewsCategoryRepository;
import com.hxc.cms.dao.NewsRepository;
import com.hxc.cms.enums.ResultEnum;
import com.hxc.cms.exception.ApplicationException;
import com.hxc.cms.model.NewsCategory;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.service.news.NewsCategoryService;
import com.hxc.cms.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("newsCategoryService")
public class NewsCategoryServiceImpl implements NewsCategoryService {

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;
    @Autowired
    private NewsRepository newsRepository;
    
    @Override
    public List<NewsCategory> findCategorys(NewsCategory newsCategoryParam) {

        Example<NewsCategory> example = Example.of(newsCategoryParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime","ide")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(newsCategoryParam, exampleMatcher);
        return this.newsCategoryRepository.findAll(example);
    }
    
    @Override
    @Transactional
    public void save(NewsCategory newsCategory) {
        this.newsCategoryRepository.save(newsCategory);
    }
    
    @Override
    @Transactional
    public void update(NewsCategory newsCategory) {
        this.newsCategoryRepository.save(newsCategory);
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if(ObjectUtil.isNotBlank(id)){
            this.newsRepository.deleteByCategoryId(id);
            this.newsCategoryRepository.delete(id);
        }
    }
    
    @Override
    @Transactional
    public void deletes(List<Integer> ids) {
        if(ObjectUtil.isNotBlank(ids)){
            this.newsRepository.deleteByCategoryIds(ids);
            this.newsCategoryRepository.deleteByIds(ids);
        }
    }
}
