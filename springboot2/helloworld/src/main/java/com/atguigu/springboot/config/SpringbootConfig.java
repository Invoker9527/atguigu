package com.atguigu.springboot.config;

import com.atguigu.springboot.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author YangRuiHong
 * @create 2022-06-27 19:35
 * @description:
 */
@Configuration(proxyBeanMethods = false)
public class SpringbootConfig {
    @Bean("person")
    public Person getPerson() {
        return new Person();
    }

    @Bean("tom")
    public String stringDemo() {
        return new String();
    }

    @Bean("jedis")
    public Jedis getJedis() {
        return new Jedis("192.168.198.138", 6379);
    }
}
