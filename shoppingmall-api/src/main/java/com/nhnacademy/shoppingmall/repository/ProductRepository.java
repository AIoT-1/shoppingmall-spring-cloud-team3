package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
