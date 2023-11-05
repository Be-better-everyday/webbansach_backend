package com.example.webbansach_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "purchasing_address")
    private String purchasingAddress;
    @Column(name = "shipping_address")
    private String shippingAddress;
    @Column(name = "total_product_value")
    private double totalProductValue;
    @Column(name = "shipping_fee")
    private double shippingFee;
    @Column(name = "payment_fee")
    private double paymentFee;
    @Column(name = "grand_total")
    private double grandTotal;

    @OneToMany(mappedBy = "orderInstance",  // using property name in the corresponding Entity
            fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList;

    @ManyToOne(
//            fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // this user is a buyer

    @ManyToOne(
//            fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne(
//            fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(name = "shipping_method_id")
    private ShippingMethod shippingMethod;
}
