package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.dto.CategoryDto;
import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("상품 목록 조회 성공")
    void getProductsSummary() throws Exception {
        ProductDto.ProductSummaryResponse productSummaryResponse = new ProductDto.ProductSummaryResponse(1L, "test", "testName",1000, "thumb", 100, LocalDateTime.now());
        PageResponseDto<ProductDto.ProductSummaryResponse> responsePage =  PageResponseDto.fromPage(new PageImpl<>(List.of(productSummaryResponse), PageRequest.of(0, 9), 1L));

        when(productService.getProductsSummary(any(),anyLong(),anyString()))
                .thenReturn(responsePage);
        mockMvc.perform(get("/api/products")
                .header("X-USER-ID", "1")
                .param("categoryId", "1")
                .param("keyword", "test"))
                .andExpect(status().isOk())
                .andReturn();


    }

    @Test
    @DisplayName("상품 상세 조회 성공")
    void getProductDetail() throws Exception {
        ProductDto.Read.Response productDetailResponse = ProductDto.Read.Response.class.getConstructor().newInstance();
        ReflectionTestUtils.setField(productDetailResponse, "id", 1L);
        ReflectionTestUtils.setField(productDetailResponse, "modelNumber", "test");
        ReflectionTestUtils.setField(productDetailResponse, "modelName", "testName");
        ReflectionTestUtils.setField(productDetailResponse, "description", "testDesc");
        ReflectionTestUtils.setField(productDetailResponse, "unitCost", 1000);
        ReflectionTestUtils.setField(productDetailResponse, "thumbnail", "thumb");
        ReflectionTestUtils.setField(productDetailResponse, "quantity", 100);
        ReflectionTestUtils.setField(productDetailResponse, "createdAt", LocalDateTime.now());
        ReflectionTestUtils.setField(productDetailResponse, "categories", List.of(new CategoryDto(1L, "test")));

        when(productService.getProductDetail(anyLong()))
                .thenReturn(productDetailResponse);
        mockMvc.perform(get("/api/products/1")
                        .header("X-USER-ID", "1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(productDetailResponse)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.modelNumber").value("test"))
                .andExpect(jsonPath("$.modelName").value("testName"))
                .andExpect(jsonPath("$.description").value("testDesc"))
                .andExpect(jsonPath("$.unitCost").value(1000))
                .andExpect(jsonPath("$.thumbnail").value("thumb"))
                .andExpect(jsonPath("$.quantity").value(100))
                .andExpect(jsonPath("$.categories[0].id").value(1))
                .andExpect(jsonPath("$.categories[0].name").value("test"))
                .andReturn();
    }

    @Test
    @DisplayName("상품 생성 성공")
    void createProduct() throws Exception {
        ProductDto.Create.Request request = createProductRequest();

        when(productService.createProduct(any(), anyString()))
                .thenReturn(1L);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/products")
                        .file((MockMultipartFile) request.getThumbnail())
                        .file((MockMultipartFile) request.getProductImages().get(0))
                        .file((MockMultipartFile) request.getProductImages().get(1))
                        .param("modelNumber", request.getModelNumber())
                        .param("modelName", request.getModelName())
                        .param("description", request.getDescription())
                        .param("unitCost", request.getUnitCost().toString())
                        .param("quantity", request.getQuantity().toString())
                        .param("categoryIds", request.getCategoryIds().get(0).toString())
                        .header("X-USER-ID", "1")

                )
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("상품 삭제 성공")
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1")
                        .header("X-USER-ID", "1"))
                .andExpect(status().isNoContent())
                .andReturn();
    }
    public ProductDto.Create.Request createProductRequest() {
        String modelNumber = "12345";
        String modelName = "TestProduct";
        String description = "testProduct";
        Integer unitCost = 1000;
        Integer quantity = 10;
        List<Long> categoryIds = new ArrayList<>();
        categoryIds.add(1L);
        categoryIds.add(2L);

        MockMultipartFile thumbnail = new MockMultipartFile("thumbnail", "thumbnail.jpg", "image/jpeg", new byte[10]);

        List<MultipartFile> productImages = new ArrayList<>();
        productImages.add(new MockMultipartFile("image1", "image1.jpg", "image/jpeg", new byte[10]));
        productImages.add(new MockMultipartFile("image2", "image2.jpg", "image/jpeg", new byte[10]));

        return new ProductDto.Create.Request(modelNumber, modelName, description, unitCost, quantity, categoryIds, thumbnail, productImages);
    }

}