package com.hxc.cms.dao;

import com.hxc.cms.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    
    @Modifying
    @Transactional
    @Query(value="delete from com.hxc.cms.model.Company  e where e.id in (:ids) ")
    int deleteByIds(@Param("ids")List<Integer> ids);
}
