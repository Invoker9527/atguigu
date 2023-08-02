package com.atguigu.mybatis.service.impl;

import com.atguigu.mybatis.bean.User;
import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author YangRuiHong
 * @create 2022-07-19 15:20
 * @description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
