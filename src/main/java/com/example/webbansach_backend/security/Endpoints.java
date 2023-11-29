package com.example.webbansach_backend.security;

public class Endpoints {
    public static final String FRONT_END_HOST = "http://localhost:3000/";
    public static final String[] PUBLIC_GET_ENDPOINTS = {
            "/books", "/books/**",
            "/images", "/images/**",
            "/users/search/existsByUsername", "/users/search/existsByEmail"
    };
    public static final String[] PUBLIC_POST_ENDPOINTS = {
            "/accounts/register",
            "/accounts/login"
    };

    public static final String[] ADMIN_POST_ENDPOINTS = {
            "/users", "/users/**"
    };
}
