package com.hxc.cms.service.news.impl;

import com.hxc.cms.model.NewsCategory;
import com.hxc.cms.service.news.NewsCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author 黄细初
 * @createDate 2018/08/08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsCategoryServiceImplTest {
    
    @Autowired
    private NewsCategoryService newsCategoryService;
    
    @Test
    public void save() throws Exception {
        NewsCategory newsCategory = new NewsCategory();
        newsCategory.setName("公司新闻");
        newsCategory.setIde(1);
        newsCategory.setCompanyCode("gwHvj2penyCpFW1j");
        newsCategoryService.save(newsCategory);
    }
    
}