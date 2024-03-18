package com.nhnacademy.shoppingmallfront.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private List<ProductDTO> content;
    private int totalPages;
    private int number;
    private boolean previous;
    private boolean next;
}
