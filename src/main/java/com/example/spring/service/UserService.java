package com.example.spring.service;

import com.example.spring.repository.UserRepository;
import com.example.spring.user.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<Entity> getUser() {
      return   userRepository.findAll();
    }

}
