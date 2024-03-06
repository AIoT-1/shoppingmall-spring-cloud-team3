package com.nhnacademy.shoppingmall.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProductDto {

    private ProductDto() {
    }
    @Getter
    public static class ProductSummaryRequest{
        private String keyword;
        private Long categoryId;
    }
    @Getter
    public static class ProductSummaryResponse implements Serializable {
        private final Long id;
        private final String modelNumber;
        private final String modelName;
        private final Integer unitCost;
        private final String thumbnail;
        private final Integer quantity;
        private final LocalDateTime createdAt;
        @QueryProjection
        public ProductSummaryResponse(Long id, String modelNumber, String modelName, Integer unitCost, String thumbnail, Integer quantity, LocalDateTime createdAt) {
            this.id = id;
            this.modelNumber = modelNumber;
            this.modelName = modelName;
            this.unitCost = unitCost;
            this.thumbnail = thumbnail;
            this.quantity = quantity;
            this.createdAt = createdAt;
        }
    }

}
