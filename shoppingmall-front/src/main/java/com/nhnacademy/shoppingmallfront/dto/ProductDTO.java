package com.nhnacademy.shoppingmallfront.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String modelNumber;
    private String modelName;
    private int unitCost;
    private String thumbnail;
    private int quantity;
    private LocalDateTime createdAt;
}
