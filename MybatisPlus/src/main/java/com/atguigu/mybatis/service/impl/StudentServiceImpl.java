package com.atguigu.mybatis.service.impl;

import com.atguigu.mybatis.bean.Student;
import com.atguigu.mybatis.bean.User;
import com.atguigu.mybatis.mapper.StudentMapper;
import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.service.StudentService;
import com.atguigu.mybatis.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author YangRuiHong
 * @create 2022-07-20 9:09
 * @email Yangrhd@dcits.com
 * @description:
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
