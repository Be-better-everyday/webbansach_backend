package com.example.webbansach_backend.service;

import com.example.webbansach_backend.dao.UserRepository;
import com.example.webbansach_backend.entity.ErrorNotice;
import com.example.webbansach_backend.entity.Notice;
import com.example.webbansach_backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> registerUser (User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body(new Notice("Username has been existed"));
        }
        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body(new Notice("Email has been existed"));
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok("Register successful!");
    }
}
