package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author YangRuiHong
 * @create 2022-07-18 21:38
 * @description:
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
