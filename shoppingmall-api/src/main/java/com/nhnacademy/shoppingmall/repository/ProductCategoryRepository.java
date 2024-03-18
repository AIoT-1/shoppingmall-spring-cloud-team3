package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findByProduct_Id(Long id);
}
