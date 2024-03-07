package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto.ProductSummaryResponse>> getProductsSummary(@ParameterObject @PageableDefault(size = 9) Pageable pageable
                                                                                        , @RequestParam(value = "category", required = false)Long categoryId
                                                                                        , @RequestParam(value = "keyword", required = false)String keyword){
        return ResponseEntity.ok().body(productService.getProductsSummary(pageable, categoryId, keyword));
    }

}
