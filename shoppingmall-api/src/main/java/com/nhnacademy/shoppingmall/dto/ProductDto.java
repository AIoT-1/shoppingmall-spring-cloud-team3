package com.nhnacademy.shoppingmall.dto;


import com.nhnacademy.shoppingmall.enitiy.Category;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDto {

    private ProductDto() {
    }

    @Getter
    public static class ProductSummaryRequest {
        private String keyword;
        private Long categoryId;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
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
            public static Response fromEntity(Product product, List<Category> categories) {
                Response response = new Response();
                response.id = product.getId();
                response.modelNumber = product.getModelNumber();
                response.modelName = product.getModelName();
                response.description = product.getDescription();
                response.unitCost = product.getUnitCost();
                response.thumbnail = product.getThumbnail();
                response.quantity = product.getQuantity();
                response.createdAt = product.getCreatedAt();
                response.categories = categories.stream()
                        .map((category -> new CategoryDto(category.getId(), category.getName())))
                        .collect(Collectors.toList());
                return response;

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

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
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
