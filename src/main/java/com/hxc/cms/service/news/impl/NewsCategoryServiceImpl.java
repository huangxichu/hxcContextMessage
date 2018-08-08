package com.hxc.cms.service.news.impl;

import com.hxc.cms.dao.NewsCategoryRepository;
import com.hxc.cms.dao.NewsRepository;
import com.hxc.cms.enums.ResultEnum;
import com.hxc.cms.enums.Status;
import com.hxc.cms.exception.ApplicationException;
import com.hxc.cms.model.NewsCategory;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.news.NewsCategoryService;
import com.hxc.cms.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("newsCategoryService")
public class NewsCategoryServiceImpl implements NewsCategoryService {

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;
    @Autowired
    private NewsRepository newsRepository;
    
    @Override
    public Page<NewsCategory> findCategorysByPage(NewsCategory newsCategoryParam, PageParam pageParam) {
        Example<NewsCategory> example = Example.of(newsCategoryParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime","ide")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith()) //姓名采用“开始匹配”的方式查询
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(newsCategoryParam, exampleMatcher);
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows(),Sort.Direction.ASC,"ide","createTime"); //页码：前端从1开始，jpa从0开始，做个转换
        return this.newsCategoryRepository.findAll(example,pageable);
    }
    
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
        if(!ObjectUtil.isNotBlank(newsCategory.getId())){
            newsCategory.setStatus(Status.ENABLE.getCode());
            newsCategory.setCreateTime(new Date());
            this.newsCategoryRepository.save(newsCategory);
        }else{
            NewsCategory category = this.newsCategoryRepository.getOne(newsCategory.getId());
            if(category != null){
                category.setStatus(newsCategory.getStatus());
                category.setName(newsCategory.getName());
                category.setIde(newsCategory.getIde());
                this.newsCategoryRepository.save(category);
            }
        }
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
