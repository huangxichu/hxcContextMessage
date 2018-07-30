package com.hxc.cms.dao;

import com.hxc.cms.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Page<Product> findAll(Specification<Product> spc, Pageable pageable);
}
