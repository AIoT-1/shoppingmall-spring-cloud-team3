package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getProductsSummary() throws Exception {
        ProductDto.ProductSummaryResponse productSummaryResponse = new ProductDto.ProductSummaryResponse(1L, "test", "testName",1000, "thumb", 100, LocalDateTime.now());
        PageResponseDto<ProductDto.ProductSummaryResponse> responsePage =  PageResponseDto.fromPage(new PageImpl<>(List.of(productSummaryResponse), PageRequest.of(0, 9), 1L));

        when(productService.getProductsSummary(any(),anyLong(),anyString()))
                .thenReturn(responsePage);
        mockMvc.perform(get("/products")
                .param("categoryId", "1")
                .param("keyword", "test"))
                .andExpect(status().isOk())
                .andReturn();


    }
}