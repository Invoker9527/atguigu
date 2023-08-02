package com.atguigu.mybatis;

import com.atguigu.mybatis.pojo.Pet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author YangRuiHong
 * @create 2022-07-19 10:49
 * @description:
 */
@SpringBootApplication

public class MybatisplusApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(MybatisplusApplication.class, args);
        Pet pet1 = run.getBean("pet", Pet.class);
        System.out.println(pet1);
        Pet pet = run.getBean("pet", Pet.class);
        System.out.println("pet = " + pet);
        System.out.println(pet == pet1);
    }

}
