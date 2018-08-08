package com.hxc.cms.dao;

import com.hxc.cms.model.NewsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory,Integer> {
    
    

    
    @Modifying
    @Query(value="delete from com.hxc.cms.model.NewsCategory  e where e.id in (:ids) ")
    int deleteByIds(@Param("ids")List<Integer> ids);
}
