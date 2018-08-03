package com.hxc.cms.controller.user;


import com.hxc.cms.dto.Result;
import com.hxc.cms.dto.UserLoginToken;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param loginCode
     * @param password
     * @return
     */
    @PutMapping("/user/login")
    public Result login(
            @RequestHeader(value = "salt",required = true) String salt,
            @RequestParam(value = "loginCode",required = true) String loginCode,
            @RequestParam(value = "loginCode",required = true) String password)throws Exception{
        UserInfo userParam = new UserInfo();
        userParam.setLoginCode(loginCode);
        userParam.setPassword(password);
        UserInfo user = userService.login(userParam,salt);
        String token = userService.createToKen(user);
        UserLoginToken uk = new UserLoginToken();
        uk.setToken(token);
        uk.setUserInfo(user);
        return ResultUtil.success(uk);
    }

    
    
}
