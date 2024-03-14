package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.ProductResponseDTO;

public interface CartService {
    ProductResponseDTO getProducts(int page);
}
