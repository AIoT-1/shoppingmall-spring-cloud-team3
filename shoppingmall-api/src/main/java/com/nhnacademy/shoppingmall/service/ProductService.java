package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.dto.ProductDto;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    PageResponseDto<ProductDto.ProductSummaryResponse> getProductsSummary(Pageable pageable, Long categoryId, String keyword);

    ProductDto.Read.Response getProductDetail(Long id);

    Long createProduct(ProductDto.Create.Request request, String path);
}
