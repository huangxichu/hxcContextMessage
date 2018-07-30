package com.hxc.cms.service.user;

import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.UserParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        UserInfo user = new UserInfo();
        user.setCompanyCode("0001");
        user.setEmpId(1);
        UserInfo userInfo = this.userService.save(user);
    }

    @Test
    public void getOne() {
        try {
            UserInfo userParam = new UserInfo();
            userParam.setLoginCode("hxc");
            logger.info("user={}",this.userService.getOne(userParam).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findUsersByPage() {
    }
}