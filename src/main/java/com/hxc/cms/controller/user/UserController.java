package com.hxc.cms.controller.user;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.PageResult;
import com.hxc.cms.dto.Result;
import com.hxc.cms.dto.UserLoginToken;
import com.hxc.cms.enums.ResultEnum;
import com.hxc.cms.exception.ApplicationException;
import com.hxc.cms.model.Employee;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    @Autowired
    private RedisUtil redisUtil;
    
    /**
     * 获取公钥
     * @return
     */
    @GetMapping("/user/getPk")
    public Result getPublicKey(HttpServletRequest request)throws Exception{
        Map map =  RSAUtils.genKeyPair();
//        System.out.println("publicKey:===>"+map.get("publicKey"));
//        System.out.println("privateKey:===>"+map.get("privateKey"));
        String publicKey = RSAUtils.getPublicKey(map);
        String privateKey = RSAUtils.getPrivateKey(map);
        redisUtil.set(publicKey,privateKey,60L*1, TimeUnit.SECONDS);
        return ResultUtil.success(publicKey);
    }
    
    /**
     * 登录
     * @param loginCode
     * @param password
     * @return
     */
    @PutMapping("/user/login")
    public Result login(
            @RequestHeader(value = "salt",required = true) String salt,
            @RequestParam(value = "a",required = true) String companyCode,
            @RequestParam(value = "b",required = true) String loginCode,
            @RequestParam(value = "c",required = true) String password,
            @RequestParam(value = "p",required = false) String publicKey)throws Exception{
//        Object private_key_obj = redisUtil.get(publicKey);
        String pwd = password;
//        if(private_key_obj != null){
//            String privateKey = private_key_obj.toString();
            password = Base64Utils.encode(password.getBytes());
//            pwd = new String(RSAUtils.decryptByPrivateKey(password.getBytes(),privateKey));
//            redisUtil.remove(publicKey);
//        }else{
//            throw new ApplicationException(ResultEnum.USER_LOGIN_KEY_IS_EXPIRE);
//        }
        UserInfo userParam = new UserInfo();
        userParam.setLoginCode(loginCode);
        userParam.setPassword(pwd);
        userParam.setCompanyCode(companyCode);
        UserInfo user = userService.login(userParam,salt);
        String token = userService.createToKen(user);
        UserLoginToken uk = new UserLoginToken();
        uk.setToken(token);
        uk.setUserInfo(user);
        return ResultUtil.success(uk);
    }


    @CheckLogin
    @GetMapping("/user/page")
    public Result getUsers(HttpServletRequest request,
                                  @RequestParam(value = "page",required = true) Integer page,
                                  @RequestParam(value = "rows",required = true) Integer rows,
                                  @RequestParam(value = "relName",required = false) String relName,
                                  @RequestParam(value = "phone",required = false) String phone,
                                  @RequestParam(value = "sex",required = false) String sex,
                                  @RequestParam(value = "status",required = false) String status
    ){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        UserInfo userParam = new UserInfo();
        userParam.setCompanyCode(user.getCompanyCode());
        if(ObjectUtil.isNotBlank(status)){
            userParam.setStatus(status);
        }
        Employee employeeParam = new Employee();
        if(ObjectUtil.isNotBlank(relName)){
            employeeParam.setRelName(relName);
        }
        if(ObjectUtil.isNotBlank(sex)){
            employeeParam.setSex(sex);
        }
        if(ObjectUtil.isNotBlank(phone)){
            employeeParam.setPhone(phone);
        }
        PageParam pageParam = new PageParam(ObjectUtil.numberFormat(page, Constant.PAGES),ObjectUtil.numberFormat(rows,Constant.ROWS));
        PageResult<UserInfo> userInfoPage = userService.findAll(userParam,employeeParam,pageParam);
        return ResultUtil.success(userInfoPage);
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
