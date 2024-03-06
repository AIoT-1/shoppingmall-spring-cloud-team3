package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.CategoryDto;


public interface CategoryService {

    CategoryDto.RegisterResponse registerCategory(CategoryDto.RegisterRequest categoryRegisterRequest);

    CategoryDto.ListResponse getCategories();

    CategoryDto.UpdateResponse updateCategory(Long id ,CategoryDto.UpdateRequest categoryUpdateRequest);

}
