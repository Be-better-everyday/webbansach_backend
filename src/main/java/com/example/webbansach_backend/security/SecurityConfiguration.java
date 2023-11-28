package com.example.webbansach_backend.security;

import com.example.webbansach_backend.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userService);
        dap.setPasswordEncoder(passwordEncoder());
//        dap.setPasswordEncoder();
        return dap;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                config -> config
                        .requestMatchers(HttpMethod.GET, Endpoints.PUBLIC_GET_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.POST, Endpoints.PUBLIC_POST_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, "users/**", "/users").hasAnyAuthority("ADMIN", "CREATOR")
//                        .requestMatchers(HttpMethod.GET, "users/**", "/users").authenticated()
        );
        http.cors(
                cors -> {
                    cors.configurationSource(request -> {
                        CorsConfiguration corsConfig = new CorsConfiguration();
                        corsConfig.addAllowedOrigin(Endpoints.FRONT_END_HOST);
                        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                        corsConfig.addAllowedHeader("*");
                        return corsConfig;
                    });
                });

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
