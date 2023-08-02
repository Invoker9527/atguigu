package com.atguigu.SpringSecurity.mapper;

import com.atguigu.SpringSecurity.entities.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YangRuiHong
 * @create 2022-07-28 10:50
 * @email Yangrhd@dcits.com
 * @description:
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
}
