package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.repository.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> , ProductRepositoryCustom {

    boolean existsByIdAndQuantityGreaterThanEqual(Long productId, int quantity);

}
