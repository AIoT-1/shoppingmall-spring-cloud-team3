package com.nhnacademy.shoppingmall.exception.category;

import com.nhnacademy.shoppingmall.exception.DuplicateException;

public class CategoryNameDuplicateException extends DuplicateException {
    public CategoryNameDuplicateException(String name) {
        super("Category name is duplicated: " + name);
    }
}
