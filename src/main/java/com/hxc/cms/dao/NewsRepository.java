package com.hxc.cms.dao;

import com.hxc.cms.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Integer> {
    
    @Modifying
    @Transactional
    @Query(value="delete from com.hxc.cms.model.News  e where e.newCategoryId in (:ids) ")
    int deleteByCategoryIds(@Param("ids")List<Integer> ids);
    
    
    @Modifying
    @Transactional
    @Query(value="delete from com.hxc.cms.model.News  e where e.newCategoryId = :id ")
    int deleteByCategoryId(@Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value="delete from com.hxc.cms.model.News  e where e.id in (:ids) ")
    int deleteByIds(@Param("ids")List<Integer> ids);
}
