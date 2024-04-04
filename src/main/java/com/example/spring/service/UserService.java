package com.example.spring.service;

import com.example.spring.dto.request.Request;
import com.example.spring.repository.UserRepository;
import com.example.spring.user.Entity;

import jakarta.transaction.Transactional;

import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<Entity> getUser() {
      return   userRepository.findAll();
    }

    public Entity addNewUser(Entity entity) {
        Optional<Entity> userOptional = userRepository.findUserByID(entity.getId());
        if(userOptional.isPresent()) {
            throw new IllegalStateException("User already exists");
        }else 
        return userRepository.save(entity);
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
    public Entity getParticipateEntity(Long UserID) {
        Optional<Entity> eOptional1 = userRepository.findById(UserID);
        if(!eOptional1.isPresent()) {
            throw new IllegalTransactionStateException("Can not get user :"+ UserID + "does not exist");        }
     else {
        Entity entity2 = eOptional1.get();
       Entity entity1 = new Entity();
       entity1.setId(UserID);
       entity1.setName(entity2.getName());
       entity1.setEmail(entity2.getEmail());
       entity1.setRole(entity2.getRole());
       entity1.setPassword(null);
       entity1.setStatus(entity2.getStatus());
       return entity1;
    }  
    }
    public Entity getParticipateEntityByAdmin(Long UserID) {
        Optional<Entity> eOptional1 = userRepository.findById(UserID);
        if(!eOptional1.isPresent()) {
            throw new IllegalTransactionStateException("Can not get user :"+ UserID + "does not exist");        }
     else {
        Entity entity2 = eOptional1.get();
       Entity entity1 = new Entity();
       entity1.setId(UserID);
       entity1.setName(entity2.getName());
       entity1.setEmail(entity2.getEmail());
       entity1.setRole(entity2.getRole());
       entity1.setPassword(entity2.getPassword());
       entity1.setStatus(entity2.getStatus());
       return entity1;
    }  
    }

    public Entity getListBaseOnRoleFromAccount(Long UserID) {
        Optional<Entity> eOptional = userRepository.findById(UserID);
        if (!eOptional.isPresent()) {
            throw new IllegalTransactionStateException("Can not get user :" + UserID + "does not exist");
        } else {
            Entity entity = eOptional.get();
            if (entity.getRole().equals("admin")) {
                return getParticipateEntityByAdmin(UserID);
            } else 
            return getParticipateEntityByAdmin(UserID);
        }
    }
// public List<Entity> getListBaseOnRoleFromEntities(Long UserID) {
//     Optional <Entity> eOptional = userRepository.findById(UserID);
//     if(!eOptional.isPresent()) {
//         throw new IllegalTransactionStateException("Can not get user :"+ UserID + "does not exist");        }
//  else { 
//     // Entity entity = new Entity();
//     Entity entity2 = eOptional.get();
//     if(entity2.getRole() == "admin") {
//         return List.of(getParticipateEntityByAdmin(UserID));
//     } 
//     else {
//         return List.of(getParticipateEntity(UserID));
//     }
// }
}