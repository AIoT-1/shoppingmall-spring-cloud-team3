package com.nhnacademy.shoppingmall.enitiy;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
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

    @Builder
    public Cart (User user, Product product, Integer quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    public void changeQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
