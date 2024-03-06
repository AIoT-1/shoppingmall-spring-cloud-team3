package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.CategoryDto;
import com.nhnacademy.shoppingmall.enitiy.Category;
import com.nhnacademy.shoppingmall.exception.category.CategoryNameDuplicateException;
import com.nhnacademy.shoppingmall.exception.category.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 등록 컨트롤러 테스트")
    void registerCategory() throws Exception {
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"test\"\n" +
                        "}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("카테고리 이름 중복 시 예외 발생 테스트")
    void registerCategoryWithDuplicatedName() throws Exception {
        when(categoryService.registerCategory(any())).thenThrow(CategoryNameDuplicateException.class);
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"test\"\n" +
                        "}"))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("카테고리 목록 조회 컨트롤러 테스트")
    void getCategories() throws Exception {
        when(categoryService.getCategories()).thenReturn(new CategoryDto.ListResponse(List.of(new CategoryDto(1L, "test"))));
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String contentAsString = result.getResponse().getContentAsString();
                    assertTrue(contentAsString.contains("test"));
                });
    }

    @Test
    @DisplayName("카테고리 수정 컨트롤러 테스트")
    void updateCategory() throws Exception {
        Category category = Category.builder().name("test").build();
        when(categoryService.updateCategory(any(), any())).thenReturn(CategoryDto.UpdateResponse.fromEntity(category));
        mockMvc.perform(put("/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"test\"\n" +
                        "}"))
                .andExpect(status().isOk()).andExpect(
                result -> {
                    String contentAsString = result.getResponse().getContentAsString();
                    assertTrue(contentAsString.contains("test"));
                });
    }

    @Test
    @DisplayName("카테고리 수정 시 카테고리가 없을 경우 예외 발생 테스트")
    void updateCategoryWithNoCategory() throws Exception {
        when(categoryService.updateCategory(any(), any())).thenThrow(CategoryNotFoundException.class);
        mockMvc.perform(put("/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"test\"\n" +
                        "}"))
                .andExpect(status().isNotFound());
    }
}

