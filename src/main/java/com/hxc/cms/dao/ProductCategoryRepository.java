package com.hxc.cms.dao;

import com.hxc.cms.model.Product;
import com.hxc.cms.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    Page<ProductCategory> findAll(Specification<ProductCategory> spc, Pageable pageable);
}
