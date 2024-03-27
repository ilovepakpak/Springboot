package com.example.spring.service;

import com.example.spring.dto.request.Request;
import com.example.spring.repository.UserRepository;
import com.example.spring.user.Entity;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private  UserRepository userRepository;
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
    public  void deleteUser(Long UserID) {
        boolean exists = userRepository.existsById(UserID);
        if(!exists) {
            throw new IllegalStateException("User with id " + UserID + " does not exist");
        } else {
            userRepository.deleteById(UserID);
        }
     }
    @Transactional 
    public void updateUser(Long UserID , String email , String name ) {
        Optional <Entity> eOptional = userRepository.findById(UserID);
        if(!eOptional.isPresent()) {
            throw new IllegalStateException("User with id " + UserID + " does not exist");
        } else {
            Entity entity = eOptional.get();
            if(name != null && name.length() > 0 && !entity.getName().equals(name)) {
                entity.setName(name);
            }
            if(email != null && email.length() > 0 && !entity.getEmail().equals(email)) {
                Optional<Entity> userOptional = userRepository.findUserByEmail(email);
                if(userOptional.isPresent()) {
                    throw new IllegalStateException("Email already taken");
                }
                entity.setEmail(email);
            }
        }
    }
}