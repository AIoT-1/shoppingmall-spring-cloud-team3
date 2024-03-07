package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getProductsSummary() {
        Page<ProductDto.ProductSummaryResponse> productSummaryResponse = Page.empty();
        when(productRepository.findProductSummaryBySearchOption(any(), anyString(), anyLong())).thenReturn(productSummaryResponse);
        Pageable pageable = PageRequest.of(0, 9);
        productService.getProductsSummary(pageable, 1L, "keyword");

        verify(productRepository, times(1)).findProductSummaryBySearchOption(any(), anyString(), anyLong());
    }
}