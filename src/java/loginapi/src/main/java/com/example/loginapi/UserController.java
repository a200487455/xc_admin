package com.example.loginapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.loginapi.model.User;
import com.example.loginapi.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userService.register(user)) {
            return "注册成功！";
        } else {
            return "用户名已存在！";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        if (userService.login(user)) {
            return "登录成功！";
        } else {
            return "用户名或密码错误！";
        }
    }
}
