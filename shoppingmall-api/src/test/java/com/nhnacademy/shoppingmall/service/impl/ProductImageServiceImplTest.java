package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.repository.custom.impl.ProductRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductImageServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductImageServiceImpl productImageService;


}