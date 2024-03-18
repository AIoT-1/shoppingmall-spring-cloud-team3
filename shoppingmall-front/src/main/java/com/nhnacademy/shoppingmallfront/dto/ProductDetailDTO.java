package com.nhnacademy.shoppingmallfront.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private Long id;
    private String modelNumber;
    private String modelName;
    private String description;
    private int unitCost;
    private String thumbnail;
    private int quantity;
    private LocalDateTime createdAt;
    private List<CategoryDTO> categories;
}
