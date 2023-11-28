package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUsername (@RequestParam("username") String username);

    public boolean existsByEmail (@RequestParam("email") String email);

    public User findByUsername (String username);

    public User findByEmail(String email);
}
