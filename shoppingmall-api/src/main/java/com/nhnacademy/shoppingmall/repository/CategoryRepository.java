package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.dto.CategoryDto;
import com.nhnacademy.shoppingmall.enitiy.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String categoryName);

    @Query("select new com.nhnacademy.shoppingmall.dto.CategoryDto(c.id, c.name) from Category c")
    List<CategoryDto> findAllBy();

}
