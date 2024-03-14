package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.dto.AddressDto;
import com.nhnacademy.shoppingmall.enitiy.Address;
import com.nhnacademy.shoppingmall.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    private long addressId = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;
    @Test
    void getAddressList() throws Exception {
        when(addressService.getAddressList()).thenReturn(List.of(AddressDto.Read.Response.fromEntity(createAddressEntity())));
        mockMvc.perform(get("/api/address")
                        .header("X-USER-ID", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].type").value("home"))
                .andExpect(jsonPath("$[0].zipCode").value("12345"))
                .andExpect(jsonPath("$[0].address").value("서울시 강남구 역삼동"))
                .andExpect(jsonPath("$[0].addressDetail").value("123-456"));
    }

    @Test
    void createAddress() throws Exception {

        mockMvc.perform(post("/api/address")
                        .header("X-USER-ID", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        " \"type\": \"home\"," +
                        " \"zipCode\": \"12345\"," +
                        " \"address\": \"서울시 강남구 역삼동\"," +
                        " \"addressDetail\": \"123-456\" "+
                        "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void updateAddress() throws Exception {
        when(addressService.updateAddress(anyLong(),any())).thenReturn(AddressDto.Update.Response.fromEntity(createAddressEntity()));
        mockMvc.perform(put("/api/address/1")
                        .header("X-USER-ID", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                " \"type\": \"home\"," +
                                " \"zipCode\": \"12345\"," +
                                " \"address\": \"서울시 강남구 역삼동\"," +
                                " \"addressDetail\": \"123-456\" "+
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.type").value("home"))
                .andExpect(jsonPath("$.zipCode").value("12345"))
                .andExpect(jsonPath("$.address").value("서울시 강남구 역삼동"))
                .andExpect(jsonPath("$.addressDetail").value("123-456"));

    }

    @Test
    void updateDefaultAddress() throws Exception {
        mockMvc.perform(put("/api/address/1/default")
                        .header("X-USER-ID", "1"))
                .andExpect(status().isOk());
    }

    private Address createAddressEntity() {
        Address address = Address.builder()
                .type("home")
                .zipCode("12345")
                .address("서울시 강남구 역삼동")
                .addressDetail("123-456")
                .build();
        ReflectionTestUtils.setField(address, "id", addressId++);
        return address;
    }
}
