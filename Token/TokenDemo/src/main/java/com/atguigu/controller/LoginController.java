package com.atguigu.controller;

import com.atguigu.pojo.Result;
import com.atguigu.utils.JWTUtils;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author YangRuiHong
 * @Create 2023-02-23 17:37
 * @Description
 */
@RestController
public class LoginController {


    @RequestMapping("/login")
    public Result login(String userName, String pwd) {
        Result result = new Result();
        String token = JWTUtils.gerateToken(userName);

        result.setCode(200);
        result.setMsg("登陆成功");
        result.setData(token);
        return result;
    }
}
