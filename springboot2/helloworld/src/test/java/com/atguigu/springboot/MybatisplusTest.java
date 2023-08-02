package com.atguigu.springboot;

import com.atguigu.springboot.bean.User;
import com.atguigu.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author YangRuiHong
 * @create 2022-07-19 10:37
 * @description:
 */

@SpringBootTest
public class MybatisplusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {

        System.out.println(userMapper);
        //参数为一个条件构造器，通过条件构造器查询一个list集合，如没有条件，则可以设置null为参数
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
