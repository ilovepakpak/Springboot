package com.example.spring.user;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Component;

@jakarta.persistence.Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String status;



}
