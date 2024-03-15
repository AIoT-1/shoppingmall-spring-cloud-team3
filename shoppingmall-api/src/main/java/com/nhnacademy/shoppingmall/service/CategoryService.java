package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.CategoryDto;

import java.util.List;


public interface CategoryService {

    CategoryDto.RegisterResponse registerCategory(CategoryDto.RegisterRequest categoryRegisterRequest);

    List<CategoryDto> getCategories();

    CategoryDto.UpdateResponse updateCategory(Long id ,CategoryDto.UpdateRequest categoryUpdateRequest);

}
