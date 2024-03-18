package com.nhnacademy.shoppingmall.dto;

import com.nhnacademy.shoppingmall.enitiy.Address;
import com.nhnacademy.shoppingmall.enitiy.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AddressDto {
    private Long id;
    private String type;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String defaultYn;
    public static AddressDto fromEntity(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.id = address.getId();
        addressDto.type = address.getType();
        addressDto.zipCode = address.getZipCode();
        addressDto.address = address.getAddress();
        addressDto.addressDetail = address.getAddressDetail();
        addressDto.defaultYn = address.getDefaultYn();
        return addressDto;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Read{
        @Getter
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Response{
            private Long id;
            private String type;
            private String zipCode;
            private String address;
            private String addressDetail;
            private String defaultYn;
            public static Response fromEntity(Address address){
                Response response = new Response();
                response.id = address.getId();
                response.type = address.getType();
                response.zipCode = address.getZipCode();
                response.address = address.getAddress();
                response.addressDetail = address.getAddressDetail();
                response.defaultYn = address.getDefaultYn();
                return response;
            }
        }
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        @Getter
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Request {
            private final String type;
            private final String zipCode;
            private final String address;
            private final String addressDetail;

            public Address toEntity(User user){
                return Address.builder()
                        .user(user)
                        .type(type)
                        .zipCode(zipCode)
                        .address(address)
                        .addressDetail(addressDetail)
                        //주소 추가 등록 시 기본 주소 설정 X
                        .defaultYn("N")
                        .build();
            }
        }
        @Getter
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Response {
            private Long id;
            private String type;
            private String zipCode;
            private String address;
            private String addressDetail;
            private String defaultYn;
            public static Response fromEntity(Address address){
                Response response = new Response();
                response.id = address.getId();
                response.type = address.getType();
                response.zipCode = address.getZipCode();
                response.address = address.getAddress();
                response.addressDetail = address.getAddressDetail();
                response.defaultYn = address.getDefaultYn();
                return response;
            }
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Update {

        @Getter
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Request {
            private final String type;
            private final String zipCode;
            private final String address;
            private final String addressDetail;
        }


        @Getter
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Response {
            private Long id;
            private String type;
            private String zipCode;
            private String address;
            private String addressDetail;
            private String defaultYn;
            public static Response fromEntity(Address address){
                Response response = new Response();
                response.id = address.getId();
                response.type = address.getType();
                response.zipCode = address.getZipCode();
                response.address = address.getAddress();
                response.addressDetail = address.getAddressDetail();
                response.defaultYn = address.getDefaultYn();
                return response;
            }
        }
    }
}
