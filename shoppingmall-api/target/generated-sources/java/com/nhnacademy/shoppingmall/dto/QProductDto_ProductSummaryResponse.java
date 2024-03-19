package com.nhnacademy.shoppingmall.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.nhnacademy.shoppingmall.dto.QProductDto_ProductSummaryResponse is a Querydsl Projection type for ProductSummaryResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductDto_ProductSummaryResponse extends ConstructorExpression<ProductDto.ProductSummaryResponse> {

    private static final long serialVersionUID = -646922082L;

    public QProductDto_ProductSummaryResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> modelNumber, com.querydsl.core.types.Expression<String> modelName, com.querydsl.core.types.Expression<Integer> unitCost, com.querydsl.core.types.Expression<String> thumbnail, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt) {
        super(ProductDto.ProductSummaryResponse.class, new Class<?>[]{long.class, String.class, String.class, int.class, String.class, int.class, java.time.LocalDateTime.class}, id, modelNumber, modelName, unitCost, thumbnail, quantity, createdAt);
    }

}

