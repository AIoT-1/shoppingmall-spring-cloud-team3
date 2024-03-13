package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.enitiy.Category;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.ProductCategory;
import com.nhnacademy.shoppingmall.enitiy.ProductImage;
import com.nhnacademy.shoppingmall.exception.product.ProductNotFoundException;
import com.nhnacademy.shoppingmall.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.repository.ProductImageRepository;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final CategoryRepository categoryRepository;

    public PageResponseDto<ProductDto.ProductSummaryResponse> getProductsSummary(Pageable pageable, Long categoryId, String keyword){
        return PageResponseDto.fromPage(productRepository.findProductSummaryBySearchOption(pageable, keyword, categoryId));
    }

    @Override
    public ProductDto.Read.Response getProductDetail(Long id) {

        return productRepository.findById(id)
                .map(ProductDto.Read.Response::fromEntity)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Long createProduct(ProductDto.Create.Request request) {
        Product savedProduct = productRepository.save(request.toEntity());

        // 상품 이미지 저장 로직
        List<ProductImage> productImages = request.getImages().stream()
                                                                .map(image -> new ProductImage(savedProduct, image))
                                                                .collect(Collectors.toList());
        productImageRepository.saveAll(productImages);

        // 상품 카테고리 저장 로직
        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
        List<ProductCategory> productCategories = categories.stream()
                                                                .map(category -> new ProductCategory(savedProduct, category))
                                                                .collect(Collectors.toList());

        productCategoryRepository.saveAll(productCategories);

        return savedProduct.getId();
    }
}
