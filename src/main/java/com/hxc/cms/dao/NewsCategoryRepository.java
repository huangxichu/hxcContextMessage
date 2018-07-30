package com.hxc.cms.dao;

import com.hxc.cms.model.NewsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory,Integer> {

    Page<NewsCategory> findAll(Specification<NewsCategory> spc, Pageable pageable);
}
