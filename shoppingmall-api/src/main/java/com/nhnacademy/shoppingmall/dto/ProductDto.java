package com.nhnacademy.shoppingmall.dto;


import com.nhnacademy.shoppingmall.enitiy.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ProductDto {

    private ProductDto() {
    }

    @Getter
    public static class ProductSummaryRequest {
        private String keyword;
        private Long categoryId;
    }

    @Getter
    public static class Read {
        @Getter
        public static class Response {
            private Long id;
            private String modelNumber;
            private String modelName;
            private String description;
            private Integer unitCost;
            private String thumbnail;
            private Integer quantity;
            private LocalDateTime createdAt;
            private List<CategoryDto> categories;

            @Builder
            public Response(Long id, String modelNumber, String modelName, String description, Integer unitCost, String thumbnail, Integer quantity, LocalDateTime createdAt) {
                this.id = id;
                this.modelNumber = modelNumber;
                this.modelName = modelName;
                this.description = description;
                this.unitCost = unitCost;
                this.thumbnail = thumbnail;
                this.quantity = quantity;
                this.createdAt = createdAt;
            }

            public static Response fromEntity(Product product) {
                return Response.builder()
                        .id(product.getId())
                        .modelNumber(product.getModelNumber())
                        .modelName(product.getModelName())
                        .description(product.getDescription())
                        .unitCost(product.getUnitCost())
                        .thumbnail(product.getThumbnail())
                        .quantity(product.getQuantity())
                        .createdAt(product.getCreatedAt())
                        .build();
            }
        }
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

    public static class Create{
        @Getter
        public static class Request {
            private String modelNumber;
            private String modelName;
            private String description;
            private Integer unitCost;
            private String thumbnail;
            private Integer quantity;
            @Size(min = 1, max = 3)
            private List<Long> categoryIds;
            private List<String> images;

            public Product toEntity() {
                return Product.builder()
                        .modelNumber(modelNumber)
                        .modelName(modelName)
                        .description(description)
                        .unitCost(unitCost)
                        .thumbnail(thumbnail)
                        .quantity(quantity)
                        .build();
            }
        }

    }


}
