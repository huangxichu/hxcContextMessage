package com.hxc.cms.service.news;

import com.hxc.cms.model.News;

import java.util.List;

public interface NewsService {

    List<News> findNews(News newsParam);

    void save(News news);

    void update(News news);

    void delete(Integer id);

    void deletes(List<Integer> ids);

}
