package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.Authority;
import com.example.webbansach_backend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
