package com.hxc.cms.service.product;

import com.hxc.cms.model.ProductCategory;
import com.hxc.cms.param.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductCategoryService {
    
    Page<ProductCategory> findProductCategorysByPage(ProductCategory productCategoryParam, PageParam pageParam);
    
    List<ProductCategory> findCategorys(ProductCategory productCategoryParam);
    
    void save(ProductCategory productCategory);
    
    void update(ProductCategory productCategory);
    
    void delete(Integer id);
    
    void deletes(List<Integer> ids);
    
    Page<ProductCategory> findCategorysByPage(ProductCategory productCategoryParam, PageParam pageParam);
    
}
