package com.hxc.cms.service.product.impl;

import com.hxc.cms.dao.ProductRepository;
import com.hxc.cms.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;



}
