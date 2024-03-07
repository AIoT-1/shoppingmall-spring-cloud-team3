package com.nhnacademy.shoppingmall.enitiy;


import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;
    @Column(name = "quantity")
    private Integer quantity;

}
