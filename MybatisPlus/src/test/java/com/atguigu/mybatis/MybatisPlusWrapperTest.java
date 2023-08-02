package com.atguigu.mybatis;

import com.atguigu.mybatis.bean.Student;
import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.minidev.json.writer.UpdaterMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.atguigu.mybatis.bean.User;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

/**
 * @author YangRuiHong
 * @create 2022-07-19 17:57
 * @email Yangrhd@dcits.com
 * @description:
 */
@SpringBootTest(classes = MybatisplusApplication.class)
@RunWith(SpringRunner.class)
public class MybatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //查询用户名包含a，年龄在20-30之间且邮箱不为null的数据
        queryWrapper.like("user_name", "B")
                .between("age", 20, 30).isNotNull("email");
        for (User user : userMapper.selectList(queryWrapper)) {
            System.out.println(user);
        }
    }

    @Test
    public void test02() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //查询用户信息，按照年龄的降序排序，若年龄相同，按id的升序排序
        // SELECT uid AS id,user_name AS Name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,id ASC
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void testDelete() {
        //删除邮箱地址为null的用户信息(加了逻辑删除注解后，其)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        //UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
        int delete = userMapper.delete(queryWrapper);
    }

    @Test
    public void testUpdate() {
//将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息删除
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (age > ? AND user_name LIKE ? OR email IS NULL)
        queryWrapper.gt("age", 20)
                .like("user_name", "a")
                .or().isNull("email");
        User user = new User();
        user.setName("小明");
        user.setEmail("test@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result = " + result);


    }

    @Test
    public void test05() {
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //lambda中的条件优先执行
        queryWrapper.like("user_name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();

        user.setName("小红");
        user.setEmail("test@atguigu.com");
//        UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void test06() {
        //查询用户的用户名，年龄，邮箱信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07() {
        //查询id小于等于100的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        SELECT uid AS id,user_name AS Name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid<=100))
        queryWrapper.inSql("uid", "select uid from t_user where uid<=100");//第二个参数为子查询条件
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test08() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        updateWrapper.like("user_name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("user_name", "小黑").set("email", "abc@atguigu.com");
//        UPDATE t_user SET user_name=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void test09() {
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            //判断某个字符串是否不为空字符串，不为null，不为空白符
            queryWrapper.like("username", username);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    @Test
    public void test10() {
        String username = "";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "user_name", username).ge(ageBegin != null, "age", ageBegin).le(ageEnd != null, "age", ageEnd);
    }

    @Test
    public void test11() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        SELECT uid AS id,user_name AS Name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age <= ?)
        lambdaQueryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> list = userMapper.selectList(lambdaQueryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void testPageVo() {
        Page<User> page = new Page<User>();
//        select uid, user_name, age, email from t_user where age > ? LIMIT ?
        userMapper.selectPageVo(page, 20);
        System.out.println("page = " + page);
    }
}
