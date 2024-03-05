package com.nhnacademy.shoppingmall.enitiy;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "point")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "record_date")
    @CreationTimestamp
    private LocalDate recordDate;
}
