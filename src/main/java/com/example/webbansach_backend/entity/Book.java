package com.example.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "book_name", length = 255)
    private String bookName;
    private String author;
    @Column(name = "isbn", length = 255)
    private String ISBN;
    @Column(columnDefinition = "text")
    private String desc;
    @Column(name = "list_price")
    private double listPrice;
    @Column(name = "actual_price")
    private double actualPrice;
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
    @Column(name = "average_star")
    private double averageStar;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(
            name = "book_book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "book_category_id")
    )
    private List<BookCategory> bookCategoryList;

    @OneToMany(mappedBy = "book",  // using property name in the corresponding Entity
            fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Image> imageList;

    @OneToMany(mappedBy = "book",  // using property name in the corresponding Entity
            fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Review> reviewList;

/*  _ When we delete a book, we can't delete "OrderDetail" ==> calculate in tax, total will be wrong */
    @OneToMany(mappedBy = "book",  // using property name in the corresponding Entity
            fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    private List<OrderDetail> orderDetailList;
    @OneToMany(mappedBy = "book",  // using property name in the corresponding Entity
            fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
    private List<favouriteBook> favouriteBookList;
}
