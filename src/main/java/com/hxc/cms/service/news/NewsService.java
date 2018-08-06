package com.hxc.cms.service.news;

import com.hxc.cms.model.News;
import com.hxc.cms.param.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsService {
    
    Page<News> findNewsByPage(News newsParam, PageParam pageParam);
    
    List<News> findNews(News newsParam);

    void save(News news);

    void update(News news);

    void delete(Integer id);

    void deletes(List<Integer> ids);

}
