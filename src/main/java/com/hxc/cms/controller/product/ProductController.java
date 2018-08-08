package com.hxc.cms.controller.product;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.Product;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.product.ProductService;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.Constant;
import com.hxc.cms.utils.ObjectUtil;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    
    @CheckLogin
    @GetMapping("/page/products")
    public Result getProductsByPage(HttpServletRequest request,
                                    @RequestParam(value = "page",required = true) Integer page,
                                    @RequestParam(value = "rows",required = true) Integer rows){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Product productParam = new Product();
        productParam.setCompanyCode(user.getCompanyCode());
        PageParam pageParam = new PageParam(ObjectUtil.numberFormat(page, Constant.PAGES),ObjectUtil.numberFormat(rows,Constant.ROWS));
        Page<Product> products = productService.findProductsByPage(productParam,pageParam);
        return ResultUtil.success(products);
    }
    
    @CheckLogin
    @GetMapping("/products")
    public Result getProducts(HttpServletRequest request){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Product productParam = new Product();
        productParam.setCompanyCode(user.getCompanyCode());
        List<Product> products = productService.findProducts(productParam);
        return ResultUtil.success(products);
    }
    
    
    @CheckLogin
    @PostMapping("/product/save")
    public Result saveProduct(HttpServletRequest request,Product product){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        product.setCompanyCode(user.getCompanyCode());
        productService.save(product);
        return ResultUtil.success(product);
    }
    
    @CheckLogin
    @PutMapping("/product/update")
    public Result update(HttpServletRequest request,Product product){
        productService.update(product);
        return ResultUtil.success(product);
    }
    
    @CheckLogin
    @DeleteMapping("/product/delete")
    public Result delete(HttpServletRequest request,Product product){
        productService.delete(product.getId());
        return ResultUtil.success(product);
    }
    
    @CheckLogin
    @DeleteMapping("/product/deletes")
    public Result deletes(HttpServletRequest request,List<Integer> ids){
        productService.deletes(ids);
        return ResultUtil.success(ids);
    }
}
