package com.atguigu.mybatis;

import com.atguigu.mybatis.bean.User;
import com.atguigu.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YangRuiHong
 * @create 2022-07-19 15:23
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisplusApplication.class)
public class MybatisPlusServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetCount() {
        //==>  Preparing: SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("count = " + count);
    }

    @Test
    public void testSaveBatch() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("ybc" + i);
            user.setAge(20 + i);
            list.add(user);
        }
//        ==>  Preparing: INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
//       Parameters: 1549301967703175170(Long), ybc0(String), 20(Integer)
//                ==> Parameters: 1549301967854170114(Long), ybc1(String), 21(Integer)
//                ==> Parameters: 1549301967921278978(Long), ybc2(String), 22(Integer)
//                ==> Parameters: 1549301967921278979(Long), ybc3(String), 23(Integer)
//                ==> Parameters: 1549301967921278980(Long), ybc4(String), 24(Integer)
//                ==> Parameters: 1549301967921278981(Long), ybc5(String), 25(Integer)
//                ==> Parameters: 1549301967921278982(Long), ybc6(String), 26(Integer)
//                ==> Parameters: 1549301967921278983(Long), ybc7(String), 27(Integer)
//                ==> Parameters: 1549301967921278984(Long), ybc8(String), 28(Integer)
//                ==> Parameters: 1549301967921278985(Long), ybc9(String), 29(Integer)
        //表示操作是否成功
        boolean b = userService.saveBatch(list);
        System.out.println("b = " + b);
    }

}
