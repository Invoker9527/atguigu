package com.invoker.springbootjpa.controller;

import com.invoker.springbootjpa.entity.User;
import com.invoker.springbootjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YangRuiHong
 * @Create 2022-01-30 16:29
 * @Description
 */
@Order(2)
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user) {
        User save = userRepository.save(user);
        return save;
    }
}
