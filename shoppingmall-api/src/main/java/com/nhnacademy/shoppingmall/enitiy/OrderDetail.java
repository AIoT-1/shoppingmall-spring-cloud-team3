package com.nhnacademy.shoppingmall.enitiy;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order order;
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;
    @Column(name = "quantity")
    private Integer quantity;
}
