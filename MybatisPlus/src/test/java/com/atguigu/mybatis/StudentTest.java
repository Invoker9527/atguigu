package com.atguigu.mybatis;

import com.atguigu.mybatis.bean.Student;
import com.atguigu.mybatis.mapper.StudentMapper;
import com.atguigu.mybatis.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author YangRuiHong
 * @create 2022-07-20 9:01
 * @email Yangrhd@dcits.com
 * @description:
 */
@SpringBootTest(classes = MybatisplusApplication.class)
@RunWith(SpringRunner.class)
public class StudentTest {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @Test
    public void test() {
        List<Long> list = Arrays.asList(1l, 2l, 3l);
        List<Student> students = studentMapper.selectBatchIds(list);
        System.out.println(students.iterator().next());
    }

    @Test
    public void testDelete() {
        int result = studentMapper.deleteBatchIds(Arrays.asList(2L));
        System.out.println(result);

    }

    @Test
    public void testService() {
        QueryWrapper queryWrapper = new QueryWrapper<Student>();

        queryWrapper.between("age", 12, 15);
        Map map = studentService.getMap(queryWrapper);
        System.out.println("map = " + map);

    }

    @Test
    public void testService2() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper();
        queryWrapper.like("name", "%小%").isNotNull("age");
        Map map = studentService.getMap(queryWrapper);
        System.out.println("map = " + map);
    }
}
