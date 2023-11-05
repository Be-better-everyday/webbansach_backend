package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.PaymentMethod;
import com.example.webbansach_backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
