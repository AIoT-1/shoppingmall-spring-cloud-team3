package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.exception.product.ProductNotFoundException;
import com.nhnacademy.shoppingmall.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

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

    @Test
    void getProductDetail_WhenProductExists_ReturnsProductDetail() {
        when(productCategoryRepository.findByProduct_Id(anyLong())).thenReturn(new ArrayList<>());
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(createProduct()));

        productService.getProductDetail(1L);

        verify(productRepository, times(1)).findById(anyLong());
        verify(productCategoryRepository, times(1)).findByProduct_Id(anyLong());
    }

    @Test
    void getProductDetail_WhenProductDoesNotExist_ThrowsProductNotFoundException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> productService.getProductDetail(1L))
                .isInstanceOf(ProductNotFoundException.class);

        verify(productRepository, times(1)).findById(anyLong());
    }
    
    @Test
    void deleteProduct_WhenProductExists_DeletesProduct() {
        when(productRepository.existsById(anyLong())).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteProduct_WhenProductDoesNotExist_ThrowsProductNotFoundException() {
        when(productRepository.existsById(anyLong())).thenReturn(false);

        Assertions.assertThatThrownBy(() -> productService.deleteProduct(1L))
                .isInstanceOf(ProductNotFoundException.class);

        verify(productRepository, times(1)).existsById(anyLong());
    }

    private Product createProduct() {
        return Product.builder()
                .modelNumber("modelNumber")
                .modelName("modelName")
                .unitCost(1000)
                .quantity(10)
                .description("description")
                .thumbnail("thumbnail")
                .build();
    }
}