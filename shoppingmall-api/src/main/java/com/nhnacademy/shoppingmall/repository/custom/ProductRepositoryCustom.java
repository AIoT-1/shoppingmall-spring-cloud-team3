package com.nhnacademy.shoppingmall.repository.custom;

import com.nhnacademy.shoppingmall.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductRepositoryCustom {
    Page<ProductDto.ProductSummaryResponse> findProductSummaryBySearchOption(Pageable pageable, String keyword, Long categoryId);

}
