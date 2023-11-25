package com.example.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "book_category")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
//    private Long categoryId;
    private Integer categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(
            name = "book_book_category",
            joinColumns = @JoinColumn(name = "book_category_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> bookList;
}
