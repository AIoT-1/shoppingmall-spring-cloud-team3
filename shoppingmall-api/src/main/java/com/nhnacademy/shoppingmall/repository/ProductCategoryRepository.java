package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
