package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "book-categories")
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

}
