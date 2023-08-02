package com.atguigu.springboot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YangRuiHong
 * @create 2022-06-27 20:42
 * @description:
 */
@RestController
public class ParameterController {
    @RequestMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id, @PathVariable("username") String username, @PathVariable Map<String, String> pv) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", username);
        map.put("pv", pv);
        return map;
    }

    public String demo() {
        return "";
    }
}
