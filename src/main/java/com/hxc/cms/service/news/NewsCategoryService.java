package com.hxc.cms.service.news;

import com.hxc.cms.model.NewsCategory;
import com.hxc.cms.param.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsCategoryService {
    
    Page<NewsCategory> findCategorysByPage(NewsCategory newsCategoryParam, PageParam pageParam);
    
    List<NewsCategory> findCategorys(NewsCategory newsCategoryParam);
    
    void save(NewsCategory newsCategory);
    
    void update(NewsCategory newsCategory);
    
    void delete(Integer id);
    
    void deletes(List<Integer> ids);
}
