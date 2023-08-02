package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author YangRuiHong
 * @create 2022-09-16 22:07
 * @email Yangrhd@dcits.com
 * @description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private UserMapper mapper;

    @org.junit.Test
    public void test01() {
        User user = new User();
        user.setId(1);
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq(user.getId(), 1);
        log.info(user.getEmail());
        System.out.println(mapper.selectOne(queryWrapper));
    }
}
