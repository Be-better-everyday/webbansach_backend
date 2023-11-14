package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.OrderDetail;
import com.example.webbansach_backend.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "payment-methods")
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

}
