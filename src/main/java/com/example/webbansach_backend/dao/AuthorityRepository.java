package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.Authority;
import com.example.webbansach_backend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "authorities")
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    public Authority findByAuthorityName (String authorityName);
}
