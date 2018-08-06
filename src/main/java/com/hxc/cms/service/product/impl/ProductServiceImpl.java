package com.hxc.cms.service.product.impl;

import com.hxc.cms.dao.ProductRepository;
import com.hxc.cms.model.Product;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.product.ProductService;
import com.hxc.cms.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    
    @Override
    public Page<Product> findProductsByPage(Product productParam, PageParam pageParam) {
        // 1.使用 Example。
        // 创建 Example。
        Example<Product> example = Example.of(productParam);
        // 2.使用 ExampleMatcher。
        // 创建 ExampleMatcher。
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略 id 和 createTime 字段。
                .withIgnorePaths("id", "createTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(productParam, exampleMatcher);
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows()); //页码：前端从1开始，jpa从0开始，做个转换
        return productRepository.findAll(example,pageable);
    }
    
    @Override
    public List<Product> findProducts(Product productParam) {
        Example<Product> example = Example.of(productParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime","pic","updateTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(productParam, exampleMatcher);
        return this.productRepository.findAll(example);
    }
    
    @Override
    @Transactional
    public void save(Product product) {
        this.productRepository.save(product);
    }
    
    @Override
    @Transactional
    public void update(Product product) {
        this.productRepository.save(product);
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if(ObjectUtil.isNotBlank(id)){
            this.productRepository.delete(id);
        }
    }
    
    @Override
    @Transactional
    public void deletes(List<Integer> ids) {
        if(ObjectUtil.isNotBlank(ids)){
            this.productRepository.deleteByIds(ids);
        }
    }
}
