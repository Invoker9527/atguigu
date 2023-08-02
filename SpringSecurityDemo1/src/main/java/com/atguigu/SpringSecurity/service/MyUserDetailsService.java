package com.atguigu.SpringSecurity.service;

import com.atguigu.SpringSecurity.entities.Users;
import com.atguigu.SpringSecurity.mapper.UsersMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangRuiHong
 * @create 2022-07-28 9:37
 * @email Yangrhd@dcits.com
 * @description:
 */
@Service("userDeDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //调用usermapper中的方法，根据用户名查询数据库
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        Users users = usersMapper.selectOne(queryWrapper);
        if (users == null) {//数据库没有用户名，该记录不存在，认证失败
            throw new UsernameNotFoundException("用户名不存在!");
        }

        //设置权限,给查询出来的用户设置admins权限
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        return new User(users.getUsername(), new BCryptPasswordEncoder().encode(users.getPassword()), auths);
    }
}

