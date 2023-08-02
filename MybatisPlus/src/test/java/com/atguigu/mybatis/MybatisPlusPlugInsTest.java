package com.atguigu.mybatis;

import com.atguigu.mybatis.bean.User;
import com.atguigu.mybatis.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author YangRuiHong
 * @create 2022-07-21 15:12
 * @email Yangrhd@dcits.com
 * @description:
 */
@SpringBootTest(classes = MybatisplusApplication.class)
@RunWith(SpringRunner.class)
public class MybatisPlusPlugInsTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询
     */
    @Test
    public void test01() {
        //查第一页，每页三条数据
        Page<User> page = new Page<>(1, 3);

        Page<User> page1 = userMapper.selectPage(page, null);
        System.out.println("page = " + page);
//        page.getCurrent()获取当前页码
        System.out.println(page.getPages());//获取总页数
        System.out.println(page.getRecords());//获取记录的数据
        System.out.println("==========================================");
        System.out.println(page.getTotal());//获取总的记录数，不统计被逻辑删除的
        System.out.println("******************************************");
        System.out.println(page.hasNext());//有没有下一页
        System.out.println(page.hasPrevious());//有没有上一页
    }
}
