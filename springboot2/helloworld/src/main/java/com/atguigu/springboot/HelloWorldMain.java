package com.atguigu.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * @author YangRuiHong
 * @create 2022-06-27 13:13
 * @description:
 */
@SpringBootApplication
//扫描mapper所在包
@MapperScan("com.atguigu.springboot.mapper")
public class HelloWorldMain {
    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(HelloWorldMain.class, args);
        Arrays.stream(run.getBeanDefinitionNames()).forEach(System.out::printf);

    }
}
