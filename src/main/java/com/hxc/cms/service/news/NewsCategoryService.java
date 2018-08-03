package com.hxc.cms.service.news;

import com.hxc.cms.model.NewsCategory;

import java.util.List;

public interface NewsCategoryService {
    
    List<NewsCategory> findCategorys(NewsCategory newsCategoryParam);
    
    void save(NewsCategory newsCategory);
    
    void update(NewsCategory newsCategory);
    
    void delete(Integer id);
    
    void deletes(List<Integer> ids);
}
