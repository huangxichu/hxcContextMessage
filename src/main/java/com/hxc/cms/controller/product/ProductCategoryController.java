package com.hxc.cms.controller.product;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.ProductCategory;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.service.product.ProductCategoryService;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private UserService userService;
   
    @CheckLogin
    @GetMapping("/product/categories")
    public Result getCategory(HttpServletRequest request){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        ProductCategory productCategoryParam = new ProductCategory();
        productCategoryParam.setCompanyCode(user.getCompanyCode());
        List<ProductCategory> categories = productCategoryService.findCategorys(productCategoryParam);
        return ResultUtil.success(categories);
    }
    
    
    @CheckLogin
    @PostMapping("/product/category/save")
    public Result saveCategory(HttpServletRequest request,ProductCategory productCategory){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        productCategory.setCompanyCode(user.getCompanyCode());
        productCategoryService.save(productCategory);
        return ResultUtil.success(productCategory);
    }
    
    @CheckLogin
    @PutMapping("/product/category/update")
    public Result updateCategory(HttpServletRequest request,ProductCategory productCategory){
        productCategoryService.update(productCategory);
        return ResultUtil.success(productCategory);
    }
    
    @CheckLogin
    @DeleteMapping("/product/category/delete")
    public Result deleteCategory(HttpServletRequest request,ProductCategory productCategory){
        productCategoryService.delete(productCategory.getId());
        return ResultUtil.success(productCategory);
    }
    
    @CheckLogin
    @DeleteMapping("/product/category/deletes")
    public Result deleteCategory(HttpServletRequest request,List<Integer> ids){
        productCategoryService.deletes(ids);
        return ResultUtil.success(ids);
    }
}
