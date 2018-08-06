package com.hxc.cms.service.product;

import com.hxc.cms.model.Product;
import com.hxc.cms.param.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    
    Page<Product> findProductsByPage(Product productParam, PageParam pageParam);
    
    List<Product> findProducts(Product productParam);
    
    void save(Product product);
    
    void update(Product product);
    
    void delete(Integer id);
    
    void deletes(List<Integer> ids);
}
