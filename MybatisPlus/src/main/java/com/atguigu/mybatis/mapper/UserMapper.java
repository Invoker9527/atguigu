package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author YangRuiHong
 * @create 2022-07-19 10:49
 * @description:
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    Map<String, Object> selectMapById(Long id);

    /**
     * 通过年龄查询用户信息并分页
     *
     * @param page mybatisplus提供的分页对象，必须给page对象
     * @param age
     * @return
     */
    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);
}
