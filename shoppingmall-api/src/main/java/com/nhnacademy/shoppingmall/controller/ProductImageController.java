package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.ProductImageDto;
import com.nhnacademy.shoppingmall.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products/{productId}/images")
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping
    public ResponseEntity<List<ProductImageDto.Read.Response>> getImages(@PathVariable Long productId){
        return ResponseEntity.ok().body(productImageService.getProductImages(productId));
    }

}

