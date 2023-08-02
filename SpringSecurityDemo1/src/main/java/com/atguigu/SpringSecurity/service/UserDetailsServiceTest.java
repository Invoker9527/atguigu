//package com.atguigu.SpringSecurity.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author YangRuiHong
// * @create 2022-08-01 9:13
// * @email Yangrhd@dcits.com
// * @description:
// */
//@Service("UserDetailsService")
//public class UserDetailsServiceTest implements UserDetailsService {
//    @Autowired
//    public BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        List<GrantedAuthority> rols = AuthorityUtils.createAuthorityList("rols");
//        return new User("xiaoming", bCryptPasswordEncoder.encode("999"), rols);
//    }
//}
