package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.enitiy.Category;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.ProductCategory;
import com.nhnacademy.shoppingmall.enitiy.ProductImage;
import com.nhnacademy.shoppingmall.exception.file.FileUploadException;
import com.nhnacademy.shoppingmall.exception.product.ProductNotFoundException;
import com.nhnacademy.shoppingmall.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.repository.ProductImageRepository;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public PageResponseDto<ProductDto.ProductSummaryResponse> getProductsSummary(Pageable pageable, Long categoryId, String keyword) {
        return PageResponseDto.fromPage(productRepository.findProductSummaryBySearchOption(pageable, keyword, categoryId));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto.Read.Response getProductDetail(Long id) {
        List<ProductCategory> productCategoryList = productCategoryRepository.findByProduct_Id(id);
        List<Category> categoryList = productCategoryList.stream().map(ProductCategory::getCategory).collect(Collectors.toList());
        return productRepository.findById(id)
                .map(product -> ProductDto.Read.Response.fromEntity(product, categoryList))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    private String saveFile(MultipartFile file, String uploadPath) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File saveFile = new File(uploadPath, fileName);
            file.transferTo(saveFile);
        return fileName;
    }

    @Override
    public Long createProduct(ProductDto.Create.Request request, String uploadPath) {

        List<String> productImageNameList = new ArrayList<>();
        String thumbnailFileName = null;

        try {
            if(request.getThumbnail() != null){
                thumbnailFileName = saveFile(request.getThumbnail(), uploadPath);
            }

            //상품 이미지 저장
            for (MultipartFile file : request.getProductImages()) {
                String productImageFileName = saveFile(file, uploadPath);
                productImageNameList.add(productImageFileName);
            }
        } catch (IOException e) {
            log.error("파일 저장 실패", e);
            throw new FileUploadException(e.getMessage());
        }
        Product savedProduct = productRepository.save(request.toEntity());
        savedProduct.setThumbnail(thumbnailFileName);
        // 상품 이미지 저장 로직
        List<ProductImage> productImages = productImageNameList.stream()
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

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
