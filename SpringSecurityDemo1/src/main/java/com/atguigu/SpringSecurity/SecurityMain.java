package com.atguigu.SpringSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author YangRuiHong
 * @create 2022-07-27 17:18
 * @email Yangrhd@dcits.com
 * @description:
 */

@SpringBootApplication
public class SecurityMain {
    public static void main(String[] args) {
        SpringApplication.run(SecurityMain.class, args);
    }
}
