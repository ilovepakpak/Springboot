package com.example.spring.controller;

import com.example.spring.repository.UserRepository;
import com.example.spring.service.UserService;
import com.example.spring.user.Entity;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;
    private  final UserService userService;
    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/get")
    public Entity addnewUser(@RequestBody Entity entity) {      
        if(entity!=null) {
            return userService.addNewUser(entity); // Fix: Return userService.addNewUser(entity) instead of userService.addNewUser(entity2)
        }
        else throw new IllegalStateException("User not found");
    }
    @GetMapping("/get/alluser")
    public List<Entity> get() {
        return userService.getUser();
    }
    // @GetMapping(path =  "/get/{UserID}")
    // public void getUser(@PathVariable("UserID") Long UserID) {
    //         userService.getParticipateUserById(UserID);
    // }
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
    @GetMapping(path = "/get/{UserID}") 
    public List<?> getParticipateUserById(@PathVariable("UserID") Long UserID) {
        return List.of(userService.getParticipateEntity(UserID));
    }

    @GetMapping(path =  "/get/bean/{UserID}") 
    public List<Entity> geListBaseOnRole( 
        @PathVariable("UserID" ) Long UserID , @PathVariable("Role") @RequestParam(required = true )  String Role  ) {
            List<Entity> entities = new ArrayStack<Entity>();
            entities.add(userService.getListBaseOnRoleFromAccount(UserID));
            return entities;
            
     }
}
