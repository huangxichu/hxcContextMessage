package com.hxc.cms.controller.news;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.enums.Mark;
import com.hxc.cms.enums.NewsStatus;
import com.hxc.cms.enums.Status;
import com.hxc.cms.model.News;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.news.NewsService;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.Constant;
import com.hxc.cms.utils.ObjectUtil;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    
    @CheckLogin
    @GetMapping("/news/page")
    public Result getPageNews(HttpServletRequest request,
                                  @RequestParam(value = "page",required = true) Integer page,
                                  @RequestParam(value = "rows",required = true) Integer rows,
                                  @RequestParam(value = "newCategoryId",required = false) Integer newCategoryId,
                                  @RequestParam(value = "title",required = false) String title,
                                  @RequestParam(value = "isOriginal",required = false) String isOriginal,
                                  @RequestParam(value = "sources",required = false) String sources,
                                  @RequestParam(value = "status",required = false) String status
    ){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        News newsParam = new News();
        newsParam.setCompanyCode(user.getCompanyCode());
        if(ObjectUtil.isNotBlank(newCategoryId)){
            newsParam.setNewCategoryId(newCategoryId);
        }
        if(ObjectUtil.isNotBlank(title)){
            newsParam.setTitle(title);
        }
        if(ObjectUtil.isNotBlank(isOriginal)){
            newsParam.setIsOriginal(isOriginal);
        }
        if(ObjectUtil.isNotBlank(sources)){
            newsParam.setSources(sources);
        }
        if(ObjectUtil.isNotBlank(status)){
            newsParam.setStatus(status);
        }
        PageParam pageParam = new PageParam(ObjectUtil.numberFormat(page, Constant.PAGES),ObjectUtil.numberFormat(rows,Constant.ROWS));
        Page<News> categories = newsService.findNewsByPage(newsParam,pageParam);
        return ResultUtil.success(categories);
    }
    
    @GetMapping("/home/top/news")
    public Result getHomeProducts(HttpServletRequest request,
                                  @RequestParam(value = "companyCode",required = true) String companyCode){
        News param = new News();
        param.setCompanyCode(companyCode);
        param.setStatus(NewsStatus.OPEN.getCode());
        param.setIsTop(Mark.YES.getCode());
        List<News> newses = this.newsService.findNews(param);
        News news = ObjectUtil.isNotBlank(newses) ? newses.get(0) : null;
        return ResultUtil.success(news);
    }
    
    @GetMapping("/home/news")
    public Result getHomeProducts(HttpServletRequest request,
                                  @RequestParam(value = "companyCode",required = true) String companyCode,
                                  @RequestParam(value = "page",required = true) Integer page,
                                  @RequestParam(value = "rows",required = true) Integer rows){
        News param = new News();
        param.setCompanyCode(companyCode);
        param.setStatus(NewsStatus.OPEN.getCode());
        param.setIsTop(Mark.NO.getCode());
        PageParam pageParam = new PageParam(ObjectUtil.numberFormat(page, Constant.PAGES),ObjectUtil.numberFormat(rows,Constant.HOME_NEWS_ROWS));
        Page<News> productPage = newsService.findNewsByPage(param,pageParam);
        List<News> products = productPage != null ? productPage.getContent() : new ArrayList<>();
        return ResultUtil.success(products);
    }
    
    @CheckLogin
    @GetMapping("/news")
    public Result getNews(HttpServletRequest request){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        News newsParam = new News();
        newsParam.setCompanyCode(user.getCompanyCode());
        List<News> news = newsService.findNews(newsParam);
        return ResultUtil.success(news);
    }
    
    
    @CheckLogin
    @PostMapping("/news/save")
    public Result saveNews(HttpServletRequest request,News news){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        news.setCompanyCode(user.getCompanyCode());
        newsService.save(news);
        return ResultUtil.success(news);
    }
    
    @CheckLogin
    @PutMapping("/news/update")
    public Result update(HttpServletRequest request,News news){
        newsService.update(news);
        return ResultUtil.success(news);
    }
    
    @CheckLogin
    @DeleteMapping("/news/delete")
    public Result delete(HttpServletRequest request,News news){
        newsService.delete(news.getId());
        return ResultUtil.success(news);
    }
    
    @CheckLogin
    @DeleteMapping("/news/deletes")
    public Result deletes(HttpServletRequest request,List<Integer> ids){
        newsService.deletes(ids);
        return ResultUtil.success(ids);
    }
}
