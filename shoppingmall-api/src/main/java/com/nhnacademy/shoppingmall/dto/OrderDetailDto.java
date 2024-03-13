package com.nhnacademy.shoppingmall.dto;

import com.nhnacademy.shoppingmall.enitiy.Order;
import com.nhnacademy.shoppingmall.enitiy.OrderDetail;
import com.nhnacademy.shoppingmall.enitiy.Product;
import lombok.Getter;

@Getter
public class OrderDetailDto {
    private Long orderId;
    private Long productId;
    private int quantity;

    public OrderDetail toEntity(Order order, Product product) {
        return OrderDetail.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .build();
    }
    public OrderDetailDto(Long orderId, Long productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public static OrderDetailDto fromEntity(OrderDetail orderDetail) {
        return new OrderDetailDto(orderDetail.getOrder().getId(), orderDetail.getProduct().getId(), orderDetail.getQuantity());
    }

    @Getter
    public static class OrderDetailResponse {
        private ProductDto.Read.Response product;
        private int quantity;

        public OrderDetailResponse(ProductDto.Read.Response response, Integer quantity) {
            this.product = response;
            this.quantity = quantity;
        }
        public static OrderDetailResponse fromEntity(OrderDetail orderDetail) {
            return new OrderDetailResponse(ProductDto.Read.Response.fromEntity(orderDetail.getProduct()), orderDetail.getQuantity());
        }
    }

    @Getter
    public static class ReadResponse {
        private Long id;
        private Long productId;
        private String modelNumber;
        private String modelName;
        private int orderQuantity;

        public ReadResponse(Long id, ProductDto.Read.Response product, int orderQuantity) {
            this.id = id;
            this.productId = product.getId();
            this.modelNumber = product.getModelNumber();
            this.modelName = product.getModelName();
            this.orderQuantity = orderQuantity;
        }
        public static ReadResponse fromEntity(OrderDetail orderDetail) {
            return new ReadResponse(orderDetail.getId(), ProductDto.Read.Response.fromEntity(orderDetail.getProduct()), orderDetail.getQuantity());
        }
    }
}
