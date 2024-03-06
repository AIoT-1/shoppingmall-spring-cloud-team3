package com.nhnacademy.shoppingmall.enitiy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product {
    @Id
    @Getter
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
    @ColumnDefault("'N'")
    private String deletedYn;

    @Builder
    public Product(String modelNumber, String modelName, Integer unitCost, String description, String thumbnail, Integer quantity) {
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.unitCost = unitCost;
        this.description = description;
        this.thumbnail = thumbnail;
        this.quantity = quantity;
    }
}
