package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.ProductDetailDTO;
import com.nhnacademy.shoppingmallfront.dto.ProductImageDTO;
import com.nhnacademy.shoppingmallfront.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO getProducts(int page, int categoryId, String keyword);
    ProductDetailDTO getProduct(Long productId);
    List<ProductImageDTO> getImages(int productId);
}
