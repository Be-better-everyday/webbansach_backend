package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "reviews")
public interface ShippingMethodRepository extends JpaRepository<Review, Long> {

}
