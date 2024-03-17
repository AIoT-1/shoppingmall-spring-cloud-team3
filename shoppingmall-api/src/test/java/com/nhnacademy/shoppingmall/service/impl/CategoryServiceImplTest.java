package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.CategoryDto;
import com.nhnacademy.shoppingmall.enitiy.Category;
import com.nhnacademy.shoppingmall.exception.category.CategoryNameDuplicateException;
import com.nhnacademy.shoppingmall.exception.category.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    @DisplayName("카테고리 등록 테스트")
    void registerCategory() {
        when(categoryRepository.existsByName(anyString())).thenReturn(false);
        when(categoryRepository.save(any())).thenReturn(createCategory());
        categoryService.registerCategory(createRegisterRequest());
        verify(categoryRepository, times(1)).existsByName(anyString());
        verify(categoryRepository, times(1)).save(any());

    }
    @Test
    @DisplayName("카테고리 이름 중복 시 예외 발생 테스트")
    void registerCategoryWithDuplicatedName() {
        when(categoryRepository.existsByName(anyString())).thenReturn(true);
        CategoryDto.RegisterRequest registerRequest = createRegisterRequest();
        Assertions.assertThatThrownBy(() -> categoryService.registerCategory(registerRequest))
                .isInstanceOf(CategoryNameDuplicateException.class);
    }

    @Test
    @DisplayName("카테고리 목록 조회 테스트")
    void getCategories() {
        Category category = createCategory();
        when(categoryRepository.findAll()).thenReturn(List.of(Category.builder().name("test").build()));
        List<CategoryDto> categories = categoryService.getCategories();
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("카테고리 수정 테스트")
    void updateCategory() {
        Category category = createCategory();
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        CategoryDto.UpdateResponse updateResponse = categoryService.updateCategory(category.getId(), createUpdateRequest());
        verify(categoryRepository, times(1)).findById(anyLong());

    }
    @Test
    @DisplayName("카테고리 수정 시 카테고리가 없을 경우 예외 발생 테스트")
    void updateCategoryWithNotExists() {
        CategoryDto.UpdateRequest updateRequest = createUpdateRequest();
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> categoryService.updateCategory(0L, updateRequest))
                .isInstanceOf(CategoryNotFoundException.class);
        verify(categoryRepository, times(1)).findById(anyLong());
    }

    private CategoryDto.UpdateRequest createUpdateRequest() {
        CategoryDto.UpdateRequest updateRequest = new CategoryDto.UpdateRequest();
        ReflectionTestUtils.setField(updateRequest, "name", "test");
        return updateRequest;
    }
    private CategoryDto.RegisterRequest createRegisterRequest() {
        CategoryDto.RegisterRequest registerRequest = new CategoryDto.RegisterRequest();
        ReflectionTestUtils.setField(registerRequest, "name", "test");
        return registerRequest;
    }
    private Category createCategory() {
        Category category = Category.builder().name("test").build();
        ReflectionTestUtils.setField(category, "id", 1L);
        return category;
    }

}