package com.hxc.cms.service.user;

import com.hxc.cms.model.Employee;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.param.UserParam;
import com.hxc.cms.utils.Constant;
import com.hxc.cms.utils.ObjectUtil;
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
    public void save() throws Exception {
        UserInfo user = new UserInfo();
//        user.setEmpId(1);
        Employee employee = new Employee();
        employee.setId(1);
        user.setLoginCode("hxc2");
        user.setPassword("123456");
        user.setCompanyCode("gwHvj2penyCpFW1j");
        user.setEmployee(employee);
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
        UserInfo userParam = new UserInfo();
        userParam.setLoginCode("hxc");
        Employee employeeParam = new Employee();
        employeeParam.setRelName("黄细初");
        PageParam pageParam = new PageParam(ObjectUtil.numberFormat(1, Constant.PAGES),ObjectUtil.numberFormat(10,Constant.ROWS));
        logger.info("users={}",this.userService.findAll(userParam,employeeParam,pageParam).toString());
    }

    @Test
    public void findUsersByPage() {
    }
}