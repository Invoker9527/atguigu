package com.atguigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author YangRuiHong
 * @Create 2023-03-05 19:47
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigNacosClient3377Main {
    public static void main(String[] args) {
        SpringApplication.run(ConfigNacosClient3377Main.class, args);
    }

}
