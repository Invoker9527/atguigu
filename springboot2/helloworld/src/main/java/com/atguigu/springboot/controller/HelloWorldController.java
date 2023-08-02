package com.atguigu.springboot.controller;

import com.atguigu.springboot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author YangRuiHong
 * @create 2022-06-27 13:14
 * @description:
 */
@Controller
public class HelloWorldController {
    @Resource
    private Person person;
    @Resource
    private Jedis jedis;


    @GetMapping("/hello/{name}/{id}")
    @ResponseBody
    public String helloWorld(@PathVariable("name") String name, @PathVariable("id") Long id) {

        jedis.setex(name, 30, String.valueOf(id));
        return "hello Spriongboot2 " + id;

    }

    @GetMapping("/result/{key}")
    @ResponseBody
    public String result(@PathVariable("key") String key) {
        return jedis.get(key);
    }

    @RequestMapping("/a.jyg")
    public String a() {
        return "aaaaa";
    }

    @RequestMapping("/person")
    @ResponseBody
    public Person getPerson() {
        person.setName("王六一");
        person.setAge(23);
        person.setSex("男");
        System.out.println("person = " + person);
        return person;
    }

    @GetMapping("/")
    @ResponseBody
    public String as() {
        return "猪酿是傻逼";
    }

    @RequestMapping("/person2")
    @ResponseBody
    public Person getPerson2() {
        person.setName("赵无极");
        person.setAge(53);
        person.setSex("男");
        System.out.println("person = " + person);
        return person;
    }


    @GetMapping("/hi/{id}")
    @ResponseBody
    public String test(@PathVariable("id") Integer id) {
        return id + "";
    }
}
