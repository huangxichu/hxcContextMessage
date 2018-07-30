package com.hxc.cms.dao;

import com.hxc.cms.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Integer> {

    Page<News> findAll(Specification<News> spc, Pageable pageable);
}
