package com.hxc.cms.service.user.impl;

import com.hxc.cms.dao.UserRepository;
import com.hxc.cms.enums.ResultEnum;
import com.hxc.cms.exception.ApplicationException;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.param.UserParam;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.Constant;
import com.hxc.cms.utils.MD5Utils;
import com.hxc.cms.utils.ObjectUtil;
import com.hxc.cms.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("userService")
public class UserServiceImpl implements UserService {
    
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisUtil redisUtil;
    
    
    @Override
    @Transient
    public UserInfo save(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    @Override
    public List<UserInfo> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<UserInfo> findUsersByPage(UserInfo userParam,PageParam pageParam) {
        // 1.使用 Example。
        // 创建 Example。
        Example<UserInfo> userExample = Example.of(userParam);
        // 2.使用 ExampleMatcher。
        // 创建 ExampleMatcher。
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略 id 和 createTime 字段。
                .withIgnorePaths("id", "createTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        userExample = Example.of(userParam, exampleMatcher);
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows()); //页码：前端从1开始，jpa从0开始，做个转换
        return userRepository.findAll(userExample,pageable);
    }

    @Override
    public UserInfo getOne(UserInfo userParam)throws Exception {
        if(!ObjectUtil.isNotBlank(userParam.getLoginCode())){
            throw new ApplicationException(ResultEnum.LOGINCODE_IS_NULL);
        }
        Example<UserInfo> userExample = Example.of(userParam);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 忽略字段。
                .withIgnorePaths("id", "createTime","password","lastLoginTime")
                // 忽略大小写。
                .withIgnoreCase()
                // 忽略为空字段。
                .withIgnoreNullValues();
        // 携带 ExampleMatcher。
        userExample = Example.of(userParam, exampleMatcher);
        return this.userRepository.findOne(userExample);
    }
    
    /**
     * Token校验
     * @param token 用户登录令牌
     * @return 校验结果，校验正确返回true，否则返回false
     * @date  2018/7/30
     * @author  黄细初
     */
    @Override
    public boolean checkToken(String token) {
        Object object = redisUtil.get(token);
        if(object == null){
            return false;
        }
        String userIdStr = object.toString();
        logger.info("redis 获取 TOKEN："+token+" ，返回值：" + userIdStr);
        if(!ObjectUtil.isNotBlank(userIdStr)){
            logger.error(String.format("获取不到用戶信息，Token：%s, memberIdStr：%s", token, userIdStr));
        }else{
            if(!ObjectUtil.isNotBlank(userIdStr)){
                //重置一下TOKEN过期时间
                String userTokenKey = String.format(Constant.USER_TOKEN_KEY, userIdStr);
                redisUtil.set(token, userIdStr, Constant.TIMEOUT_SECONDS, TimeUnit.SECONDS );
                redisUtil.set(userTokenKey, token, Constant.TIMEOUT_SECONDS, TimeUnit.SECONDS );
            }
            return true;
        }
        return false;
    }
    
    @Override
    public UserInfo login(UserInfo userParam, String salt)throws Exception {
        if(!ObjectUtil.isNotBlank(userParam.getLoginCode())){
            throw new ApplicationException(ResultEnum.LOGINCODE_IS_NULL);
        }
        if(!ObjectUtil.isNotBlank(userParam.getPassword())){
            throw new ApplicationException(ResultEnum.PASSWORD_IS_NULL);
        }
        UserInfo user = this.getOne(userParam);
        if(user != null){
            String password = user.getPassword();
            if(password.equals(userParam.getPassword())){
                String token = this.createToKen(user);//更新.生成用户Token
            }else{
                throw new ApplicationException(ResultEnum.PASSWORD_IS_ERROR);
            }
        }else{
            throw new ApplicationException(ResultEnum.USER_NOT_FIND);
        }
        return null;
    }
    
    public String createToKen(UserInfo userInfo) {
        Integer id = userInfo.getId();
        String userTokenKey = String.format(Constant.USER_TOKEN_KEY, id.toString());
        Object o = redisUtil.exists(userTokenKey);
        String token = null;
        if (ObjectUtil.isNotBlank(o)) {
            token = o.toString();
            redisUtil.remove(token);
            logger.info("更新用户登录token");
        }
        token = MD5Utils.encode(id.toString() + System.currentTimeMillis());
        //设置code的缓存，key未token
        redisUtil.set(token, String.valueOf(id), Constant.TIMEOUT_SECONDS, TimeUnit.SECONDS);
        redisUtil.set(userTokenKey, token, Constant.TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return token;
    }
    
    //查询User，单表，多条件
//    @Override
//    public List<User> findAll(int pageNum, int pageSize, User user) {
//        Pageable pageable = new PageRequest(pageNum, pageSize);
//        List<User> uList = userRepository.findAll(new Specification<User>() {
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//                List<Predicate> predicates = new ArrayList<>();
//                if (user.getUserId() != null && !user.getUserId().equals("")) {
//                    predicates.add(cb.like(root.get("userId").as(String.class), "%" + user.getUserId() + "%"));
//                }
//                if (user.getUserName() != null && !user.getUserName().equals("")) {
//                    predicates.add(cb.like(root.get("userName").as(String.class), "%" + user.getUserName() + "%"));
//                }
//                if (user.getGender() != null && !user.getGender().equals("")) {
//                    predicates.add(cb.like(root.get("gender").as(String.class), "%" + user.getGender() + "%"));
//                }
//                if (user.getAge() != null && !user.getAge().equals("")) {
//                    predicates.add(cb.like(root.get("age").as(String.class), "%" + user.getAge() + "%"));
//                }
//                Predicate[] pre = new Predicate[predicates.size()];
//                criteriaQuery.where(predicates.toArray(pre));
//                return cb.and(predicates.toArray(pre));
//            }
//        }, pageable);
//
//        return uList;
//    }

    //查询User，多表，多条件
//    @Override
//    public List<User> findAll(int pageNum, int pageSize, Params params) {
//        Pageable pageable = new PageRequest(pageNum, pageSize);
//        List<User> uList = userRepository.findAll(new Specification<User>() {
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//                List<Predicate> list = new ArrayList<>();
//                //根据userId 查询user
//                if (ObjectUtil.isNotBlank(params.getUserId())) {
//                    list.add(cb.equal(root.get("userId").as(String.class), params.getUserId()));
//                }
//                //根据userName 模糊查询user
//                if (ObjectUtil.isNotBlank(params.getUserName())) {
//                    list.add(cb.like(root.get("userName").as(String.class), "%" + params.getUserName() + "%"));
//                }
//                //根据gender 查询user
//                if (ObjectUtil.isNotBlank(params.getGender())) {
//                    list.add(cb.equal(root.get("gender").as(String.class), params.getGender()));
//                }
//                //根据age>? 查询user
//                if (ObjectUtil.isNotBlank(params.getAge())) {
//                    list.add(cb.gt(root.get("age").as(Integer.class), Integer.valueOf(params.getAge())));
//                }
//                //根据gradeName 查询user
//                if (ObjectUtil.isNotBlank(params.getGradeName())) {
//                    Join<Grade, User> join = root.join("grade", JoinType.LEFT);
//                    list.add(cb.equal(join.get("gradeName"), params.getGradeName()));
//                }
//                //根据schoolName 查询user
//                if (ObjectUtil.isNotBlank(params.getSchoolName())) {
//                    Join<School, User> join = root.join("grade", JoinType.LEFT);
//                    list.add(cb.equal(join.get("school").get("schoolName"), params.getSchoolName()));
//                }
//                Predicate[] pre = new Predicate[list.size()];
//                criteriaQuery.where(list.toArray(pre));
//                return cb.and(list.toArray(pre));
//            }
//        }, pageable);
//
//        return uList;
//    }

}
