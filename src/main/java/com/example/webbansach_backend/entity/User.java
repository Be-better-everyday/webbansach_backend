package com.example.webbansach_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    private String username;
    private String password;
    private char gender;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "purchasing_address")
    private String purchasingAddress;
    @Column(name = "shipping_address")
    private String shippingAddress;
//    @JsonIgnore
    private boolean enabled = false;
    @JsonIgnore
    @Column(name = "verification_code")
    private String verificationCode;

    @OneToMany(mappedBy = "user",  // using property name in the corresponding Entity
            fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    private List<Review> reviewList;

    @OneToMany(mappedBy = "user",  // using property name in the corresponding Entity
            fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    private List<FavouriteBook> favouriteBookList;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorityList;

    @OneToMany(mappedBy = "user",  // using property name in the corresponding Entity
            fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    private List<Order> orderList;


//    @Override
    public String getPassword() {
        System.out.println("password: " + this.password);
        return this.password;
    }
    public String getFullName()
    {
        return this.firstName + " " + this.lastName;
    }
}
