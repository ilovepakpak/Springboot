package com.example.spring.controller;

import com.example.spring.service.UserService;
import com.example.spring.user.Entity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/get")
    public List<Entity> get() {
        return new UserService().saveUser(new Entity());
    }
}
