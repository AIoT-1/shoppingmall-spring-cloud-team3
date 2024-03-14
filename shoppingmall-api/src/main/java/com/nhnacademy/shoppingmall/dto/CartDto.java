package com.nhnacademy.shoppingmall.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.nhnacademy.shoppingmall.enitiy.Cart;
import com.nhnacademy.shoppingmall.enitiy.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartDto {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Read {
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        public static class Response {
            private Long id;
            private Long productId;
            private String modelNumber;
            private String modelName;
            private String thumbnail;
            private Integer unitCost;
            private Integer cartQuantity;

            public static Response toEntity(Cart cart){
                Response response = new Response();
                Product product = cart.getProduct();
                response.id = cart.getId();
                response.productId = product.getId();
                response.modelNumber = product.getModelNumber();
                response.modelName = product.getModelName();
                response.unitCost = product.getUnitCost();
                response.thumbnail = product.getThumbnail();
                response.cartQuantity = cart.getQuantity();
                return response;
            }
        }
    }
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        public static class Request {
            @NotNull(message = "상품 ID는 필수입니다")
            private final Long productId;
            @Min(value = 1, message = "최소 수량은 1개입니다")
            private final Integer quantity;
        }
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        public static class Response {

            private Long id;
            private Long productId;
            private Integer quantity;

            public static Response fromEntity(Cart cart){
                Response response = new Response();
                response.id = cart.getId();
                response.productId = cart.getProduct().getId();
                response.quantity = cart.getQuantity();
                return response;
            }
        }
    }


    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Update {
        @Getter
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class Request {
            private Integer quantity;
        }
        @Getter
        public static class Response {
            private final Long id;
            private final Long productId;
            private final Integer quantity;
            public Response(Long id, Long productId, Integer quantity) {
                this.id = id;
                this.productId = productId;
                this.quantity = quantity;
            }
            public static Response fromEntity(Cart cart){
                return new Response(cart.getId(), cart.getProduct().getId(), cart.getQuantity());
            }
        }
    }
}
