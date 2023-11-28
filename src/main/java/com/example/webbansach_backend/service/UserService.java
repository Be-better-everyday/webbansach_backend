package com.example.webbansach_backend.service;

import com.example.webbansach_backend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUsername (String username);

}
