package com.example.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
//    private Long imageId;
    private int imageId;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "is_icon")
    private boolean isIcon;
    @Column(name = "link")
    private String link;
    // using "LONGTEXT" to store "Base64" image
    @Column(name = "image_data", columnDefinition = "LONGTEXT")
    @Lob
    private String imageData; // using String or Blob (use Blob when data capacity is big)

    @ManyToOne(
//            fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "book_id", nullable = false)
    /*  delete book then delete all image and a image always have a relationship with a Book*/
    private Book book;
}
