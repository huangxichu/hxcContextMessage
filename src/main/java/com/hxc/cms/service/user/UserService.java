package com.hxc.cms.service.user;

import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.param.UserParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    UserInfo save(UserInfo userInfo)throws Exception ;

    List<UserInfo> findAll();
    
    long count(UserInfo userParam);
    
    Page<UserInfo> findUsersByPage(UserInfo userParam,PageParam pageParam);

    UserInfo getOne(UserInfo userParam)throws Exception;
    
    boolean checkToken(String token);
    
    UserInfo login(UserInfo userParam, String salt)throws Exception ;
    
    String createToKen(UserInfo userInfo);
    
    UserInfo getUserInfoByToken(String token);
    
    List<UserInfo> findUserInfoes(UserInfo userParam);
    
    void update(UserInfo userInfo);
    
    void delete(Integer id);
    
    void deletes(List<Integer> ids);
    
}
