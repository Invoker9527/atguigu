//package com.atguigu.SpringSecurity.config;
//
//import com.atguigu.SpringSecurity.service.UserDetailsServiceTest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * @author YangRuiHong
// * @create 2022-08-01 9:09
// * @email Yangrhd@dcits.com
// * @description:
// */
//@Configuration
//public class SecurityConfigTest extends WebSecurityConfigurerAdapter {
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String password = bCryptPasswordEncoder.encode("999");
//        auth.inMemoryAuthentication().withUser("xiaoming").password(password).roles("admin");
//
//    }
//}
