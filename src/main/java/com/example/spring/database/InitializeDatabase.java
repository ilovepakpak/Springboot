package com.example.spring.database;

import com.example.spring.user.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InitializeDatabase extends JpaRepository<Entity, Long>{
}
