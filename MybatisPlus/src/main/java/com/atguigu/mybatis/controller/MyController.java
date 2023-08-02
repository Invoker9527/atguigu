package com.atguigu.mybatis.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author YangRuiHong
 * @create 2022-07-21 17:45
 * @email Yangrhd@dcits.com
 * @description:
 */
@Controller
public class MyController {
    @Value("${maps}")
    private Map map;

    @GetMapping("/")
    public String index() {
        System.out.println(map.get("k1"));
        return "index";
    }
}
