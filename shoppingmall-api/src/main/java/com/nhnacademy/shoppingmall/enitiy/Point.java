package com.nhnacademy.shoppingmall.enitiy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private LocalDateTime recordDate;

    @Builder
    private Point(User user, String transactionType, Integer amount) {
        this.user = user;
        this.transactionType = transactionType;
        this.amount = amount;
    }
}
