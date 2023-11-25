package com.example.webbansach_backend.rest;

import com.example.webbansach_backend.entity.User;
import com.example.webbansach_backend.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody User registerUser){
        return accountService.registerUser(registerUser);
    }
}
