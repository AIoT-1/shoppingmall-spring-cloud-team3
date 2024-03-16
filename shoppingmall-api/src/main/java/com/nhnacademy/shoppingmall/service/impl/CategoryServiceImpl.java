package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.CategoryDto;
import com.nhnacademy.shoppingmall.enitiy.Category;
import com.nhnacademy.shoppingmall.exception.category.CategoryNameDuplicateException;
import com.nhnacademy.shoppingmall.exception.category.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public CategoryDto.RegisterResponse registerCategory(CategoryDto.RegisterRequest categoryRegisterRequest) {
        String categoryName = categoryRegisterRequest.getName();
        if(categoryRepository.existsByName(categoryName)){
            throw new CategoryNameDuplicateException(categoryName);
        }
        Category category = categoryRegisterRequest.toEntity();
        Category savedCategory = categoryRepository.save(category);
        return CategoryDto.RegisterResponse.fromEntity(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CategoryDto.UpdateResponse updateCategory(Long id, CategoryDto.UpdateRequest categoryUpdateRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        category.update(categoryUpdateRequest.getName());
        return CategoryDto.UpdateResponse.fromEntity(category);
    }


}