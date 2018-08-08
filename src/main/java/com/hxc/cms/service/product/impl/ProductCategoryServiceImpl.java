package com.hxc.cms.service.product.impl;

import com.hxc.cms.dao.ProductCategoryRepository;
import com.hxc.cms.enums.Status;
import com.hxc.cms.model.ProductCategory;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.product.ProductCategoryService;
import com.hxc.cms.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    
    @Override
    public Page<ProductCategory> findCategorysByPage(ProductCategory productCategoryParam, PageParam pageParam) {
        Example<ProductCategory> example = Example.of(productCategoryParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime","ide")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith()) //姓名采用“开始匹配”的方式查询
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(productCategoryParam, exampleMatcher);
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows(),Sort.Direction.ASC,"ide","createTime"); //页码：前端从1开始，jpa从0开始，做个转换
        return this.productCategoryRepository.findAll(example,pageable);
    }
    
    @Override
    public Page<ProductCategory> findProductCategorysByPage(ProductCategory productCategoryParam, PageParam pageParam) {
        Example<ProductCategory> example = Example.of(productCategoryParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime","ide")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(productCategoryParam, exampleMatcher);
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows(),Sort.Direction.ASC,"ide","createTime"); //页码：前端从1开始，jpa从0开始，做个转换
        return this.productCategoryRepository.findAll(example,pageable);
    }
    
    @Override
    public List<ProductCategory> findCategorys(ProductCategory productCategoryParam) {
        Example<ProductCategory> example = Example.of(productCategoryParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime","ide")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        example = Example.of(productCategoryParam, exampleMatcher);
        return this.productCategoryRepository.findAll(example);
    }
    
    @Override
    @Transactional
    public void save(ProductCategory productCategory) {
        if(!ObjectUtil.isNotBlank(productCategory.getId())){
            productCategory.setStatus(Status.ENABLE.getCode());
            productCategory.setCreateTime(new Date());
            this.productCategoryRepository.save(productCategory);
        }else{
            ProductCategory category = this.productCategoryRepository.getOne(productCategory.getId());
            if(category != null){
                category.setStatus(productCategory.getStatus());
                category.setName(productCategory.getName());
                category.setIde(productCategory.getIde());
                this.productCategoryRepository.save(category);
            }
        }
    }
    
    @Override
    @Transactional
    public void update(ProductCategory productCategory) {
        this.productCategoryRepository.save(productCategory);
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if(ObjectUtil.isNotBlank(id)){
            this.productCategoryRepository.delete(id);
        }
    }
    
    @Override
    @Transactional
    public void deletes(List<Integer> ids) {
        if(ObjectUtil.isNotBlank(ids)){
            this.productCategoryRepository.deleteByIds(ids);
        }
    }
    

}
