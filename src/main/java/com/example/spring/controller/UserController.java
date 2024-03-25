package com.example.spring.controller;

import com.example.spring.service.UserService;
import com.example.spring.user.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/get")
    public List<Entity> get() {
        return userService.getUser();
    }
    @PostMapping("/post")
    public void registeruser(Entity entity) {
        userService.addNewUser(entity);

    }
}
