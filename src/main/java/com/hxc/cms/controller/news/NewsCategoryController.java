package com.hxc.cms.controller.news;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.NewsCategory;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.news.NewsCategoryService;
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
public class NewsCategoryController {

    @Autowired
    private NewsCategoryService newsCategoryService;
    @Autowired
    private UserService userService;
    
    
    @CheckLogin
    @GetMapping("/news/page/categories")
    public Result getPageCategory(HttpServletRequest request,
                                  @RequestParam(value = "page",required = true) Integer page,
                                  @RequestParam(value = "rows",required = true) Integer rows,
                                  @RequestParam(value = "name",required = false) String name,
                                  @RequestParam(value = "status",required = false) String status
                                  ){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        NewsCategory newsCategoryParam = new NewsCategory();
        newsCategoryParam.setCompanyCode(user.getCompanyCode());
        if(ObjectUtil.isNotBlank(name)){
            newsCategoryParam.setName(name);
        }
        if(ObjectUtil.isNotBlank(status)){
            newsCategoryParam.setStatus(status);
        }
        PageParam pageParam = new PageParam(ObjectUtil.numberFormat(page, Constant.PAGES),ObjectUtil.numberFormat(rows,Constant.ROWS));
        Page<NewsCategory> categories = newsCategoryService.findCategorysByPage(newsCategoryParam,pageParam);
        return ResultUtil.success(categories);
    }
    
//    @CheckLogin
    @GetMapping("/news/categories")
    public Result getCategory(HttpServletRequest request,
                              @RequestParam(value = "companyCode",required = true) String companyCode,
                              @RequestParam(value = "status",required = false) String status){
        NewsCategory newsCategoryParam = new NewsCategory();
        newsCategoryParam.setCompanyCode(companyCode);
        if(ObjectUtil.isNotBlank(status)){
            newsCategoryParam.setStatus(status);
        }
        List<NewsCategory> categories = newsCategoryService.findCategorys(newsCategoryParam);
        return ResultUtil.success(categories);
    }
    
    
    @CheckLogin
    @PostMapping("/news/category/save")
    public Result saveCategory(HttpServletRequest request,NewsCategory newsCategory){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        newsCategory.setCompanyCode(user.getCompanyCode());
        newsCategoryService.save(newsCategory);
        return ResultUtil.success(newsCategory);
    }
    
    @CheckLogin
    @PutMapping("/news/category/update")
    public Result updateCategory(HttpServletRequest request,NewsCategory newsCategory){
        newsCategoryService.update(newsCategory);
        return ResultUtil.success(newsCategory);
    }
    
    @CheckLogin
    @DeleteMapping("/news/category/delete")
    public Result deleteCategory(HttpServletRequest request,NewsCategory newsCategory){
        newsCategoryService.delete(newsCategory.getId());
        return ResultUtil.success(newsCategory);
    }
    
    @CheckLogin
    @DeleteMapping("/news/category/deletes")
    public Result deleteCategory(HttpServletRequest request,List<Integer> ids){
        newsCategoryService.deletes(ids);
        return ResultUtil.success(ids);
    }
}
