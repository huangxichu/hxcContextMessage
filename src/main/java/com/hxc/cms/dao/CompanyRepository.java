package com.hxc.cms.dao;

import com.hxc.cms.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Page<Company> findAll(Specification<Company> spc, Pageable pageable);

}
