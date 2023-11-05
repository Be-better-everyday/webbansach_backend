package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.FavouriteBook;
import com.example.webbansach_backend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
