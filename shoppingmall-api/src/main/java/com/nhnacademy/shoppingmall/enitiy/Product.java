package com.nhnacademy.shoppingmall.enitiy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "model_number")
    private String modelNumber;
    @Column(name = "model_name")
    private String modelName;
    @Column(name = "unit_cost")
    private Integer unitCost;
    @Column(name = "description")
    private String description;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "deleted_yn")
    @ColumnDefault("N")
    private String deletedYn;
}
