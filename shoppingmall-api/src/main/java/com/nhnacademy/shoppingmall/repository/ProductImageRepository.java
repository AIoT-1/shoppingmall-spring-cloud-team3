package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProduct_Id(Long productId);
}
