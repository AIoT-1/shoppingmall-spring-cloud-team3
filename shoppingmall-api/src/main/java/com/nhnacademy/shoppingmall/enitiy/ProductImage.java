package com.nhnacademy.shoppingmall.enitiy;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;
}
