package com.hxc.cms.controller.user;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.dto.UserLoginToken;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    /**
     * 登录
     * @param loginCode
     * @param password
     * @return
     */
    @PutMapping("/user/login")
    public Result login(
            @RequestHeader(value = "salt",required = true) String salt,
            @RequestParam(value = "a",required = false) String companyCode,
            @RequestParam(value = "b",required = false) String loginCode,
            @RequestParam(value = "c",required = false) String password)throws Exception{
        UserInfo userParam = new UserInfo();
        userParam.setLoginCode(loginCode);
        userParam.setPassword(password);
        userParam.setCompanyCode(companyCode);
        UserInfo user = userService.login(userParam,salt);
        String token = userService.createToKen(user);
        UserLoginToken uk = new UserLoginToken();
        uk.setToken(token);
        uk.setUserInfo(user);
        return ResultUtil.success(uk);
    }
    
    
    @CheckLogin
    @GetMapping("/users")
    public Result getUsers(HttpServletRequest request){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        UserInfo userParam = new UserInfo();
        userParam.setCompanyCode(user.getCompanyCode());
        List<UserInfo> userInfoes = userService.findUserInfoes(userParam);
        return ResultUtil.success(userInfoes);
    }
    
    
    @CheckLogin
    @PostMapping("/user/save")
    public Result saveUser(HttpServletRequest request,UserInfo userInfo)throws Exception {
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        userInfo.setCompanyCode(user.getCompanyCode());
        userService.save(userInfo);
        return ResultUtil.success(userInfo);
    }
    
    @CheckLogin
    @PutMapping("/user/update")
    public Result update(HttpServletRequest request,UserInfo userInfo){
        userService.update(userInfo);
        return ResultUtil.success(userInfo);
    }
    
    @CheckLogin
    @DeleteMapping("/user/delete")
    public Result delete(HttpServletRequest request,UserInfo userInfo){
        userService.delete(userInfo.getId());
        return ResultUtil.success(userInfo);
    }
    
    @CheckLogin
    @DeleteMapping("/user/deletes")
    public Result deletes(HttpServletRequest request,List<Integer> ids){
        userService.deletes(ids);
        return ResultUtil.success(ids);
    }
}
