package com.hxc.cms.dao;

import com.hxc.cms.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Modifying
    @Transactional
    @Query(value="delete from com.hxc.cms.model.Employee  e where e.id in (:ids) ")
    int deleteByIds(@Param("ids")List<Integer> ids);

}
