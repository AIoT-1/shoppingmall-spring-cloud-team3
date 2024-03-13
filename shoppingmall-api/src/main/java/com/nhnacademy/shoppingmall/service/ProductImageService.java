package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.ProductImageDto;

import java.util.List;

public interface ProductImageService {

    List<ProductImageDto.Read.Response> getProductImages(Long productId);

}
