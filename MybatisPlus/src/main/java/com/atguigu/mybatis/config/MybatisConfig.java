package com.atguigu.mybatis.config;

import com.atguigu.mybatis.pojo.Pet;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author YangRuiHong
 * @create 2022-07-21 15:08
 * @email Yangrhd@dcits.com
 * @description:
 */
@Configuration
@MapperScan("com.atguigu.mybatis.mapper")
public class MybatisConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //构造器参数为数据库类型
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public Jedis jedis() {
        return new Jedis("192.168.198.138", 6379);
    }

    @Bean//默认单实例
    public Pet pet() {
        return new Pet();
    }
}
