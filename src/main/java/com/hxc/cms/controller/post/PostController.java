package com.hxc.cms.controller.post;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.Post;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.post.PostService;
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
public class PostController {
    
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    
    
    @CheckLogin
    @GetMapping("/post/page")
    public Result getPostByPage(HttpServletRequest request,
                                  @RequestParam(value = "page",required = true) Integer page,
                                  @RequestParam(value = "rows",required = true) Integer rows,
                                  @RequestParam(value = "postName",required = false) String postName,
                                  @RequestParam(value = "status",required = false) String status
    ){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Post postParam = new Post();
        postParam.setCompanyCode(user.getCompanyCode());
        if(ObjectUtil.isNotBlank(postName)){
            postParam.setPostName(postName);
        }
        if(ObjectUtil.isNotBlank(status)){
            postParam.setStatus(status);
        }
        PageParam pageParam = new PageParam(ObjectUtil.numberFormat(page, Constant.PAGES),ObjectUtil.numberFormat(rows,Constant.ROWS));
        Page<Post> categories = postService.findPostsByPage(postParam,pageParam);
        return ResultUtil.success(categories);
    }
    
    @CheckLogin
    @GetMapping("/postes")
    public Result getPostes(HttpServletRequest request){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Post postParam = new Post();
        postParam.setCompanyCode(user.getCompanyCode());
        List<Post> postes = postService.findPostes(postParam);
        return ResultUtil.success(postes);
    }
    
    
    @CheckLogin
    @PostMapping("/post/save")
    public Result savePost(HttpServletRequest request,Post post){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        post.setCompanyCode(user.getCompanyCode());
        postService.save(post);
        return ResultUtil.success(post);
    }
    
    @CheckLogin
    @PutMapping("/post/update")
    public Result update(HttpServletRequest request,Post post){
        postService.update(post);
        return ResultUtil.success(post);
    }
    
    @CheckLogin
    @DeleteMapping("/post/delete")
    public Result delete(HttpServletRequest request,Post post){
        postService.delete(post.getId());
        return ResultUtil.success(post);
    }
    
    @CheckLogin
    @DeleteMapping("/post/deletes")
    public Result deletes(HttpServletRequest request,List<Integer> ids){
        postService.deletes(ids);
        return ResultUtil.success(ids);
    }
}
