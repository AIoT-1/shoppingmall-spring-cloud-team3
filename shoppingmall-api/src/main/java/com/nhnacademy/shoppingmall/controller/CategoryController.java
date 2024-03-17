package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.CategoryDto;
import com.nhnacademy.shoppingmall.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }
    @PostMapping
    public ResponseEntity<CategoryDto.RegisterResponse> registerCategory(@RequestBody CategoryDto.RegisterRequest categoryRegisterRequest){
        CategoryDto.RegisterResponse response = categoryService.registerCategory(categoryRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto.UpdateResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryDto.UpdateRequest categoryUpdateRequest){
        CategoryDto.UpdateResponse response = categoryService.updateCategory(id, categoryUpdateRequest);
        return ResponseEntity.ok(response);
    }
}
