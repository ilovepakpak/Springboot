package com.example.spring.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@jakarta.persistence.Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
public class Entity {
    @Id()
    @SequenceGenerator(
            name = "Company_sequence",
            sequenceName = "Company_sequence"
            ,allocationSize = 50

    )
    @GeneratedValue(
            strategy = GenerationType.UUID,
            generator = "Company_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String status;

    public Entity(String name, String email, String password, String role, String status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}
