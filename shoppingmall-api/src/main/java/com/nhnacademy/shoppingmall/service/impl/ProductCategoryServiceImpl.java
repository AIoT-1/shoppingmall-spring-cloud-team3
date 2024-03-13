package com.nhnacademy.shoppingmall.service.impl;


import com.nhnacademy.shoppingmall.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {


    @Override
    public void saveCategories() {

    }
}
