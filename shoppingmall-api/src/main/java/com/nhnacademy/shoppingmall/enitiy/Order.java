package com.nhnacademy.shoppingmall.enitiy;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @Column(name = "price")
    private Integer price;
    @Column(name = "order_date")
    @CreationTimestamp
    private LocalDate orderDate;
    @Column(name = "ship_date")
    private LocalDate shipDate;
}
