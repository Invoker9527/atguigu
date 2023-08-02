package com.atguigu.mybatis;

import com.atguigu.mybatis.bean.User;
import com.atguigu.mybatis.mapper.UserMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YangRuiHong
 * @create 2022-07-19 10:55
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisplusApplication.class)
public class MybatisPlusTest {
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

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@atguigu.com");
//        Preparing: INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
//        Parameters: 1549233062160146433(Long), 张三(String), 23(Integer), zhangsan@atguigu.com(String)
        int result = userMapper.insert(user);
        System.out.println("result:  " + result);
        //通过雪花算法生成的id
        System.out.println("id:" + user.getId());
    }

    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1549233062160146433L);
        System.out.println("result: " + result);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
//==>  Preparing: DELETE FROM user WHERE name = ? AND age = ?
//==> Parameters: 张三(String), 23(Integer)
        int result = userMapper.deleteByMap(map);
        System.out.println("result = " + result);

    }

    @Test
    public void testDeleteBatchIds() {
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        //通过多个id批量删除
        int result = userMapper.deleteBatchIds(list);
        System.out.println("result = " + result);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@atguigu.com");
//        ==>Preparing:
//        UPDATE user SET name =?,email =?WHERE id =?
//==>Parameters:
//        李四(String), lisi @atguigu.com(String),4 (Long)
        int result = userMapper.updateById(user);
        System.out.println("result = " + result);
    }

    @Test
    public void testSelectById() {
//        Preparing: SELECT id,name,age,email FROM user WHERE id=?
//==> Parameters: 1(Long)
        User user = userMapper.selectById(1L);
        System.out.println("user = " + user);
    }

    @Test
    public void testSelectBybatchIds() {
        List<Long> list = Arrays.asList(1L, 2l, 3l);
//        SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
//        ==> Parameters: 1(Long), 2(Long), 3(Long)
        List<User> users = userMapper.selectBatchIds(list);
        System.out.println("users = " + users);
    }

    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jack");
        map.put("age", 20);
//      ==>  Preparing: SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
//     ==> Parameters: jack(String), 20(Integer)

        List<User> users = userMapper.selectByMap(map);
        System.out.println("users = " + users);
    }

    @Test
    public void testSelectBylist() {
//        Preparing: SELECT id,name,age,email FROM user
        List<User> users = userMapper.selectByMap(null);
        System.out.println("users = " + users);
    }

    @Test
    public void testSelfDefine() {
//        Preparing: select id,name,age,email from user where id=?;
//        ==> Parameters: 1(Long)
        Map<String, Object> map = userMapper.selectMapById(1l);
        System.out.println("map = " + map);
    }
}

