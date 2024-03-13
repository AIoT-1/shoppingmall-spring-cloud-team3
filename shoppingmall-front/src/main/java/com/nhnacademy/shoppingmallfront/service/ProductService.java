package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO getProducts(int page);
}
