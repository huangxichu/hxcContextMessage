package com.hxc.cms.service.product.impl;

import com.hxc.cms.dao.ProductCategoryRepository;
import com.hxc.cms.service.product.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


}
