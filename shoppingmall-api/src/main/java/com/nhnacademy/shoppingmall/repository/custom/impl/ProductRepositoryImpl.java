package com.nhnacademy.shoppingmall.repository.custom.impl;

import com.nhnacademy.shoppingmall.dto.ProductDto;
import com.nhnacademy.shoppingmall.dto.QProductDto_ProductSummaryResponse;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.QProduct;
import com.nhnacademy.shoppingmall.enitiy.QProductCategory;
import com.nhnacademy.shoppingmall.repository.custom.ProductRepositoryCustom;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;



public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public Page<ProductDto.ProductSummaryResponse> findProductSummaryBySearchOption(Pageable pageable, String keyword, Long categoryId) {
        QProduct product = QProduct.product;
        QProductCategory productCategory = QProductCategory.productCategory;
        List<ProductDto.ProductSummaryResponse> content =
                from(product)
                .join(productCategory)
                    .on(product.id.eq(productCategory.productId))
                .where(categoryId != null ? productCategory.categoryId.eq(categoryId) : null)
                .where(keyword != null ? product.modelName.contains(keyword) : null)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .select(new QProductDto_ProductSummaryResponse(
                product.id,
                product.modelNumber,
                product.modelName,
                product.unitCost,
                product.thumbnail,
                product.quantity,
                product.createdAt
        ))
                .fetch();
        JPQLQuery<Long> count =
                from(product)
                .join(productCategory)
                .on(product.id.eq(productCategory.productId))
                .where(categoryId != null ? productCategory.categoryId.eq(categoryId) : null)
                .where(keyword != null ? product.modelName.contains(keyword) : null)
                .select(product.count());

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

}