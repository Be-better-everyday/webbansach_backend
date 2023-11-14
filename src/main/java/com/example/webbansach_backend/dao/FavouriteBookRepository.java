package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.Authority;
import com.example.webbansach_backend.entity.FavouriteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "favourite-books")
public interface FavouriteBookRepository extends JpaRepository<FavouriteBook, Long> {

}
