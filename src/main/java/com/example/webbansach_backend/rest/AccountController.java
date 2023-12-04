package com.example.webbansach_backend.rest;

import com.example.webbansach_backend.entity.User;
import com.example.webbansach_backend.security.JwtResponse;
import com.example.webbansach_backend.security.LoginRequest;
import com.example.webbansach_backend.service.AccountService;
import com.example.webbansach_backend.service.JwtService;
import com.example.webbansach_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtService jwtService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody User registerUser){
        return accountService.registerUser(registerUser);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify (@RequestParam String email, @RequestParam String verificationCode){
        ResponseEntity<?> response = accountService.verify(email, verificationCode);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {
        System.out.println("____");
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            System.out.println("*** Authentication");
            System.out.println(authentication);
            if(authentication.isAuthenticated()){
                System.out.println("inner if is authenticated");
                final String jwt = jwtService.generateToken(loginRequest.getUsername());

                System.out.println("Username: " + jwtService.extractUsername(jwt));
                System.out.println("jwt");
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        }catch (Exception exception) {
            return ResponseEntity.badRequest().body("Username or password is incorrect!");
        }
        return ResponseEntity.badRequest().body("Authentication fails!");
    }
}
