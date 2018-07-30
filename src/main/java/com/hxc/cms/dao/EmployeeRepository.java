package com.hxc.cms.dao;

import com.hxc.cms.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Page<Employee> findAll(Specification<Employee> spc, Pageable pageable);
}
