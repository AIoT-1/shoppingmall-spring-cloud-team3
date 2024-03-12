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

        private List<Long> cartIdList;
        private Long addressId;
        public Order toEntity(User user, Address address) {
            return Order.builder()
                    .user(user)
                    .address(address)
                    .build();
        }
    }

    @Getter
    public static class RegisterResponse {
        private Long id;
        private UserDto.Read.Response user;
        private AddressDto address;
        private Integer price;
        private LocalDateTime orderDate;
        private LocalDateTime shipDate;

        public RegisterResponse(Long id, UserDto.Read.Response response, AddressDto address, Integer price, LocalDateTime orderDate, LocalDateTime shipDate) {
            this.id = id;
            this.user = response;
            this.address = address;
            this.price = price;
            this.orderDate = orderDate;
            this.shipDate = shipDate;
        }
        public static RegisterResponse fromEntities(Order order, Address address) {
            return new RegisterResponse(order.getId(), UserDto.Read.Response.fromEntity(order.getUser()), AddressDto.fromEntity(address), order.getPrice(), order.getOrderDate(), order.getShipDate());
        }
    }



}
