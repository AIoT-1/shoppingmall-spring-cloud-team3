package com.nhnacademy.shoppingmall.dto;


import com.nhnacademy.shoppingmall.enitiy.Cart;
import lombok.AccessLevel;
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
            private Integer quantity;

            public static Response toEntity(Cart cart){
                Response response = new Response();
                response.id = cart.getId();
                response.productId = cart.getProduct().getId();
                response.quantity = cart.getQuantity();
                return response;
            }
        }
    }
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @Getter
        public static class Request {
            @NotNull(message = "상품 ID는 필수입니다")
            private Long productId;
            @Min(value = 1, message = "최소 수량은 1개입니다")
            private Integer quantity;
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
}
