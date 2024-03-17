package com.nhnacademy.shoppingmall.dto;

import com.nhnacademy.shoppingmall.enitiy.Address;
import com.nhnacademy.shoppingmall.enitiy.Order;
import com.nhnacademy.shoppingmall.enitiy.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDto {

    @Getter
    public static class ReadResponse{
        private Long id;

        private Integer price;
        private LocalDateTime orderDate;

        public ReadResponse(Long id, Integer price, LocalDateTime orderDate) {
            this.id = id;
            this.price = price;
            this.orderDate = orderDate;
        }

        public static ReadResponse fromEntity(Order order) {
            return new ReadResponse(order.getId(), order.getPrice(), order.getOrderDate());
        }
    }

    @Getter
    public static class RegisterRequest {
        private Long addressId;
        private List<Long> cartIdList;
        public Order toEntity(User user, Address address) {
            return Order.builder()
                    .user(user)
                    .address(address)
                    .build();
        }
    }



}
