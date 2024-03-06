package com.nhnacademy.shoppingmall.exception.category;

import com.nhnacademy.shoppingmall.exception.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(Long id) {
        super("Category not found: " + id);
    }
}
