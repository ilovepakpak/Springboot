package com.example.spring.controller;

import com.example.spring.repository.UserRepository;
import com.example.spring.service.UserService;
import com.example.spring.user.Entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private  final UserService userService;
    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/get")
    public List<Entity> get() {
        return userService.getUser();
    }
    private UserRepository userRepository;
    @PostMapping("/post")
    public void registeruser(Entity entity) {
        userRepository.findUserByEmail(entity.getEmail());
        if (userRepository.findUserByEmail(entity.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        userRepository.save(entity);
    }
    @DeleteMapping(path =  "{UserID}")
    public void deleteUser(@PathVariable("UserID") Long UserID) {
    userService.deleteUser(UserID); 
    }
    @PutMapping(path = "{UserID}") 
    public void updateUser1(
        @PathVariable("UserID") Long  UserID,
        @RequestParam(required =  false) String name,
        @RequestParam(required =  false) String email) {
            userService.updateUser(UserID, email, name);
    }

}
