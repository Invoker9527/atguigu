package com.atguigu.SpringSecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangRuiHong
 * @create 2022-07-27 17:27
 * @email Yangrhd@dcits.com
 * @description:
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("/index")
    public String index() {
        return "hello index";
    }

    @RequestMapping("update")
//    @Secured({"ROLE_sale", "ROLE_manager"})
    @PreAuthorize("hasAnyAuthority('admins')")
    public String update() {
        return "hello update";
    }
}
