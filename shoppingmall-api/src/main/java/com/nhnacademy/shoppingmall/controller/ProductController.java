package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<PageResponseDto<ProductDto.ProductSummaryResponse>> getProductsSummary(@ParameterObject @PageableDefault(size = 9) Pageable pageable
                                                                                        , @RequestParam(value = "category", required = false)Long categoryId
                                                                                        , @RequestParam(value = "keyword", required = false)String keyword){
        return ResponseEntity.ok().body(productService.getProductsSummary(pageable, categoryId, keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto.Read.Response> getProductDetail(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.getProductDetail(id));
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> createProduct(@ModelAttribute ProductDto.Create.Request request, HttpServletRequest req
                                   ){
        Long productId = productService.createProduct(request, req.getServletContext().getRealPath("/"));
        return ResponseEntity.ok().body(productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
