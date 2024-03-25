package com.example.spring.dto.request;

import com.example.spring.repository.UserRepository;
import com.example.spring.user.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class Request {
    private String name;
    private String email;
    private String password;
    private String role;
    private String status;

}
