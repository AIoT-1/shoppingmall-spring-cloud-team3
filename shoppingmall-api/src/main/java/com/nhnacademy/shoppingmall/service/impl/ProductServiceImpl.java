package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public Page<ProductDto.ProductSummaryResponse> getProductsSummary(Pageable pageable, Long categoryId, String keyword){
        return productRepository.findProductSummaryBySearchOption(pageable, keyword, categoryId);
    }
}
