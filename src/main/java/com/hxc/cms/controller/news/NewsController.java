package com.hxc.cms.controller.news;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.News;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.service.news.NewsService;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
   
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
