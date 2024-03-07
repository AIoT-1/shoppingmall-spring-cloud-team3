package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDto.ProductSummaryResponse> getProductsSummary(Pageable pageable, Long categoryId, String keyword);

}
