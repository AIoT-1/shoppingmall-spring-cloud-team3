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
        private Long id;
        private Long productId;
        private String modelNumber;
        private String modelName;
        private String thumbnail;
        private Integer unitCost;
        private Integer orderQuantity;

        public static OrderDetailResponse fromEntity(OrderDetail orderDetail) {
            OrderDetailResponse response = new OrderDetailResponse();
            Product product = orderDetail.getProduct();
            response.id = orderDetail.getId();
            response.productId = product.getId();
            response.modelNumber = product.getModelNumber();
            response.modelName = product.getModelName();
            response.thumbnail = product.getThumbnail();
            response.unitCost = product.getUnitCost();
            response.orderQuantity = orderDetail.getQuantity();
            return response;
        }
    }

    @Getter
    public static class ReadResponse {
        private Long id;
        private Long productId;
        private String modelNumber;
        private String modelName;
        private Integer orderQuantity;

        public static ReadResponse fromEntity(OrderDetail orderDetail) {
            ReadResponse response = new ReadResponse();
            Product product = orderDetail.getProduct();
            response.id = orderDetail.getId();
            response.productId = product.getId();
            response.modelNumber = product.getModelNumber();
            response.modelName = product.getModelName();
            response.orderQuantity = product.getQuantity();
            return response;
        }
    }
}
