package com.hxc.cms.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void set(){
        redisUtil.set("testKey","abcc",3600L,TimeUnit.SECONDS);
        System.out.println(redisUtil.get("testKey"));
    }

}