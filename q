[1mdiff --git a/src/main/java/com/example/webbansach_backend/filter/JwtFilter.java b/src/main/java/com/example/webbansach_backend/filter/JwtFilter.java[m
[1mindex feab33e..4c493f7 100644[m
[1m--- a/src/main/java/com/example/webbansach_backend/filter/JwtFilter.java[m
[1m+++ b/src/main/java/com/example/webbansach_backend/filter/JwtFilter.java[m
[36m@@ -1,2 +1,53 @@[m
[31m-package com.example.webbansach_backend.filter;public class JwtFilter {[m
[32m+[m[32mpackage com.example.webbansach_backend.filter;[m
[32m+[m
[32m+[m[32mimport com.example.webbansach_backend.service.JwtService;[m
[32m+[m[32mimport com.example.webbansach_backend.service.UserService;[m
[32m+[m[32mimport jakarta.servlet.FilterChain;[m
[32m+[m[32mimport jakarta.servlet.ServletException;[m
[32m+[m[32mimport jakarta.servlet.http.HttpServletRequest;[m
[32m+[m[32mimport jakarta.servlet.http.HttpServletResponse;[m
[32m+[m[32mimport lombok.AllArgsConstructor;[m
[32m+[m[32mimport org.springframework.beans.factory.annotation.Autowired;[m
[32m+[m[32mimport org.springframework.security.authentication.UsernamePasswordAuthenticationToken;[m
[32m+[m[32mimport org.springframework.security.core.context.SecurityContextHolder;[m
[32m+[m[32mimport org.springframework.security.core.userdetails.UserDetails;[m
[32m+[m[32mimport org.springframework.security.web.authentication.WebAuthenticationDetailsSource;[m
[32m+[m[32mimport org.springframework.stereotype.Component;[m
[32m+[m[32mimport org.springframework.web.filter.OncePerRequestFilter;[m
[32m+[m
[32m+[m[32mimport java.io.IOException;[m
[32m+[m
[32m+[m[32m@Component[m
[32m+[m[32m@AllArgsConstructor[m
[32m+[m[32mpublic class JwtFilter extends OncePerRequestFilter {[m
[32m+[m[32m//    @Autowired[m
[32m+[m[32m    private JwtService jwtService;[m
[32m+[m[32m//    @Autowired[m
[32m+[m[32m    private UserService userService;[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {[m
[32m+[m[32m        /*  Get "token" and "username" from "request" */[m
[32m+[m[32m        String authHeader = request.getHeader("Authorization");[m
[32m+[m[32m        String token = null;[m
[32m+[m[32m        String username = null;[m
[32m+[m[32m        // "Bearer" is used to marked what is the token, it can be customized.[m
[32m+[m[32m        if(authHeader != null && authHeader.startsWith("Bearer ")){[m
[32m+[m[32m            token = authHeader.substring(7);[m
[32m+[m[32m            username = jwtService.extractUsername(token);[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m        /*  Create new "token" from "username" and "userDetail" from token which is received in "request" */[m
[32m+[m[32m        if(username != null && SecurityContextHolder.getContext().getAuthentication() != null){[m
[32m+[m
[32m+[m[32m            UserDetails userDetails = userService.loadUserByUsername(username);[m
[32m+[m[32m            if(jwtService.validateToken(token, userDetails)){[m
[32m+[m[32m                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());[m
[32m+[m[32m                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));[m
[32m+[m[32m                SecurityContextHolder.getContext().setAuthentication(authToken);[m
[32m+[m[32m            }[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m        filterChain.doFilter(request, response);[m
[32m+[m[32m    }[m
 }[m
[1mdiff --git a/src/main/java/com/example/webbansach_backend/rest/AccountController.java b/src/main/java/com/example/webbansach_backend/rest/AccountController.java[m
[1mindex f074bdf..be9d6ab 100644[m
[1m--- a/src/main/java/com/example/webbansach_backend/rest/AccountController.java[m
[1m+++ b/src/main/java/com/example/webbansach_backend/rest/AccountController.java[m
[36m@@ -24,7 +24,6 @@[m [mimport javax.naming.AuthenticationException;[m
 @AllArgsConstructor[m
 public class AccountController {[m
     private AccountService accountService;[m
[31m-    @Autowired[m
     private AuthenticationManager authenticationManager;[m
     private UserService userService;[m
     private JwtService jwtService;[m
[1mdiff --git a/src/main/java/com/example/webbansach_backend/security/SecurityConfiguration.java b/src/main/java/com/example/webbansach_backend/security/SecurityConfiguration.java[m
[1mindex 437468a..9fa85bf 100644[m
[1m--- a/src/main/java/com/example/webbansach_backend/security/SecurityConfiguration.java[m
[1m+++ b/src/main/java/com/example/webbansach_backend/security/SecurityConfiguration.java[m
[36m@@ -1,6 +1,8 @@[m
 package com.example.webbansach_backend.security;[m
 [m
[32m+[m[32mimport com.example.webbansach_backend.filter.JwtFilter;[m
 import com.example.webbansach_backend.service.UserService;[m
[32m+[m[32mimport lombok.AllArgsConstructor;[m
 import org.springframework.context.annotation.Bean;[m
 import org.springframework.context.annotation.Configuration;[m
 import org.springframework.http.HttpMethod;[m
[36m@@ -10,16 +12,20 @@[m [mimport org.springframework.security.authentication.dao.DaoAuthenticationProvider[m
 import org.springframework.security.config.Customizer;[m
 import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;[m
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;[m
[32m+[m[32mimport org.springframework.security.config.http.SessionCreationPolicy;[m
 import org.springframework.security.core.Authentication;[m
 import org.springframework.security.core.AuthenticationException;[m
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;[m
 import org.springframework.security.web.SecurityFilterChain;[m
[32m+[m[32mimport org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;[m
 import org.springframework.web.cors.CorsConfiguration;[m
 [m
 import java.util.Arrays;[m
 [m
 @Configuration[m
[32m+[m[32m@AllArgsConstructor[m
 public class SecurityConfiguration {[m
[32m+[m[32m    private JwtFilter jwtFilter;[m
     @Bean[m
     public BCryptPasswordEncoder passwordEncoder() {[m
         return new BCryptPasswordEncoder();[m
[36m@@ -58,6 +64,9 @@[m [mpublic class SecurityConfiguration {[m
                         return corsConfig;[m
                     });[m
                 });[m
[32m+[m[32m        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);[m
[32m+[m[32m        /*  set "session" is "stateless"    */[m
[32m+[m[32m        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));[m
 [m
         http.httpBasic(Customizer.withDefaults());[m
         http.csrf(csrf -> csrf.disable());[m
