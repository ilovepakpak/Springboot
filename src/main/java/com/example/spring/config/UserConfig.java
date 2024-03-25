package com.example.spring.config;

import com.example.spring.repository.UserRepository;
import com.example.spring.user.Entity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            Entity huy = new Entity("Huy", "ilovepapak@gmail.com", "16032004", "admin", "active");
            Entity ha = new Entity("Ha", "ilovepaak@gmail.com", "16032004", "user", "active");
            Entity hahi = new Entity("HaHic", "iovepakpak@gmail.com", "16032004", "user", "active");
            userRepository.saveAll(List.of(huy,ha,hahi));
        };

    }
}