package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.Authority;
import com.example.webbansach_backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "books")
public interface BookRepository extends JpaRepository<Book, Long> {

}
