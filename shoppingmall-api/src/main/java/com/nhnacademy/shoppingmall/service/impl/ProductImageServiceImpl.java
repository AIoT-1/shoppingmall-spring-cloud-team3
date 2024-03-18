package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.ProductImageDto;
import com.nhnacademy.shoppingmall.enitiy.ProductImage;
import com.nhnacademy.shoppingmall.repository.ProductImageRepository;
import com.nhnacademy.shoppingmall.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    @Override
    @Transactional(readOnly = true)
    public List<ProductImageDto.Read.Response> getProductImages(Long productId) {

        List<ProductImage> productImages = productImageRepository.findByProduct_Id(productId);

        return  productImages.stream()
                     .map(ProductImageDto.Read.Response::fromEntity)
                     .collect(Collectors.toList());
    }
}
