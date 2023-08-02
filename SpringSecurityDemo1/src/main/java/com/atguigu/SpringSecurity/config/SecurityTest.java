//package com.atguigu.SpringSecurity.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @author YangRuiHong
// * @create 2022-07-28 9:31
// * @email Yangrhd@dcits.com
// * @description:配置
// */
//@Configuration
//public class SecurityTest extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(password());
//    }
//
//    @Bean
//    PasswordEncoder password() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()//自定义自己编写的登录界面
//                .loginPage("/login.html")//登录页面设置
//                .loginProcessingUrl("/user/login")//登录访问路径;
//                .defaultSuccessUrl("/test/index").permitAll()//登录成功之后，跳转路径
//                .and().authorizeRequests()
//                .antMatchers( "/user/login").permitAll()//访问这些路径不需要认证，可以直接访问
//                //当前登录用户，当其具有admins权限时才可以访问
//                .antMatchers("/test/index").hasAnyAuthority("admins")
//                .anyRequest().authenticated()
//                .and().csrf().disable();//关闭csrf保护
//    }
//}
