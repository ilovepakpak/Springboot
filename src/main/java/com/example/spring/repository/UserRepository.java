package com.example.spring.repository;

import com.example.spring.user.Entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<Entity, Long>{
    @org.springframework.data.jpa.repository.Query("SELECT s FROM Entity s WHERE s.email = ?1")
    Optional<Entity> findUserByEmail(String email);
    
}
