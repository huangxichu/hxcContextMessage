package com.hxc.cms.dao;

import com.hxc.cms.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    Page<Department> findAll(Specification<Department> spc, Pageable pageable);

}
