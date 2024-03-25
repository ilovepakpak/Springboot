package com.example.spring.service;

import com.example.spring.dto.request.Request;
import com.example.spring.repository.UserRepository;
import com.example.spring.user.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
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

    public void addNewUser(Entity entity) {
        System.out.println(entity);
    }
    public Entity userCreationRequest(Request request) {
        Entity entity = new Entity();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setRole(request.getRole());
        entity.setStatus(request.getStatus());
        return entity;
    }
}
