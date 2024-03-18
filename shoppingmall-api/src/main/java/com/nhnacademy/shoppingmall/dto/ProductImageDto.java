package com.nhnacademy.shoppingmall.dto;

import com.nhnacademy.shoppingmall.enitiy.ProductImage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductImageDto {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Read {
        @Getter
        public static class Response {
            private Long id;
            private String image;
            public Response(Long id, String image) {
                this.id = id;
                this.image = image;
            }

            public static Response fromEntity(ProductImage productImage) {
                return new Response(productImage.getId(), productImage.getImage());
            }

        }
    }
}
