package com.atguigu.springboot.bean;

import redis.clients.jedis.Jedis;

/**
 * @author YangRuiHong
 * @create 2022-06-27 19:33
 * @description:
 */

public class Person {
    private String name;
    private Integer age;
    private String sex;

    public Person() {

    }

    public Person(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
