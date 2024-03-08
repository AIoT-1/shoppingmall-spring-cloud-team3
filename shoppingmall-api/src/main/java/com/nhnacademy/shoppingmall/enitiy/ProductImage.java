package com.nhnacademy.shoppingmall.enitiy;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @Column(name = "image")
    private String image;

    public ProductImage(Product product, String image) {
        this.product = product;
        this.image = image;
    }

    public ProductImage() {

    }
}
