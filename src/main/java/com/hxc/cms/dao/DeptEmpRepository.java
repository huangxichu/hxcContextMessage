package com.hxc.cms.dao;

import com.hxc.cms.model.DeptEmp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptEmpRepository extends JpaRepository<DeptEmp,Integer> {

    Page<DeptEmp> findAll(Specification<DeptEmp> spc, Pageable pageable);

}
