package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.Image;
import com.example.webbansach_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
