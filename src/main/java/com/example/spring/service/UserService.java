package com.example.spring.service;

import com.example.spring.user.Entity;

import java.util.List;

public class UserService extends Entity {
    Entity entity = new Entity();
    public List<Entity> saveUser(Entity entity) {
        entity.setName("John Doe");
        entity.setEmail("ilovepakpak@");
        entity.setRole("admin");
        entity.setStatus("active");
        entity.setPassword("123456");
        return List.of(entity);
    }
}
