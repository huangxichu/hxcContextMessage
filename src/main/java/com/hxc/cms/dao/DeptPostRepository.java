package com.hxc.cms.dao;

import com.hxc.cms.model.DeptPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptPostRepository extends JpaRepository<DeptPost,Integer> {

    Page<DeptPost> findAll(Specification<DeptPost> spc, Pageable pageable);

}
