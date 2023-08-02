package com.atguigu.mybatis.test;

import com.atguigu.mybatis.MybatisplusApplication;
import com.atguigu.mybatis.bean.User;
import com.atguigu.mybatis.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author YangRuiHong
 * @create 2022-09-16 22:26
 * @email Yangrhd@dcits.com
 * @description:
 */
@Slf4j
@SpringBootTest(classes = MybatisplusApplication.class)
@RunWith(SpringRunner.class)
public class Test2 {
    @Autowired
    private UserMapper mapper;

    @Test
    public void test01() {
        String userName = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(userName), User::getName, userName)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> list = mapper.selectList(queryWrapper);
        log.info(list.toString());


    }
}
