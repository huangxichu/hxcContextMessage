package com.hxc.cms.service.user;

import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.param.UserParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    UserInfo save(UserInfo userInfo);

    List<UserInfo> findAll();

    Page<UserInfo> findUsersByPage(UserInfo userParam,PageParam pageParam);

    UserInfo getOne(UserInfo userParam)throws Exception;
    
    boolean checkToken(String token);
    
    UserInfo login(UserInfo userParam, String salt)throws Exception ;
    
}
