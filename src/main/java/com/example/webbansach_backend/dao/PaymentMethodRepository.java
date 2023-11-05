package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.OrderDetail;
import com.example.webbansach_backend.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

}
