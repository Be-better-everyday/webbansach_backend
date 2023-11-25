package com.example.webbansach_backend.dao;

import com.example.webbansach_backend.entity.Authority;
import com.example.webbansach_backend.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "books") 
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByBookNameContaining (@RequestParam("bookName") String bookName, Pageable pageable);
    Page<Book> findByBookCategoryList_CategoryId(@RequestParam("categoryId")int categoryId, Pageable pageable);

    Page<Book> findByBookNameContainingAndBookCategoryList_CategoryId(@RequestParam("bookName") String bookName, @RequestParam("categoryId")int categoryId, Pageable pageable);
}
