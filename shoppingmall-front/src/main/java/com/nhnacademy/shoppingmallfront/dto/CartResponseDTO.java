package com.nhnacademy.shoppingmallfront.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {
    private Long id;
    private Long productId;
    private String modelNumber;
    private String modelName;
    private String thumbnail;
    private int unitCost;
    private int cartQuantity;
}