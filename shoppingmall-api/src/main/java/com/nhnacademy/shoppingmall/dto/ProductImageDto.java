package com.nhnacademy.shoppingmall.dto;

import com.nhnacademy.shoppingmall.enitiy.ProductImage;

public class ProductImageDto {
    public static class Read {

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
