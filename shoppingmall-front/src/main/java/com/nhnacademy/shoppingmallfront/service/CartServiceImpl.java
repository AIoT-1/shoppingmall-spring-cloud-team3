package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.CartModifyRequestDTO;
import com.nhnacademy.shoppingmallfront.dto.CartRegisterRequestDTO;
import com.nhnacademy.shoppingmallfront.dto.CartResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    private final RestTemplate restTemplate;

    @Value("${shoppingmall_server_url}")
    private String serverURL;

    public CartServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CartResponseDTO> getCart() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-USER-ID", "1");

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<CartResponseDTO>> response = this.restTemplate.exchange(serverURL + "/carts",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<CartResponseDTO>>() {}
        );

        return response.getBody();
    }


    @Override
    public void addCart(CartRegisterRequestDTO request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-USER-ID", "1");

        HttpEntity<CartRegisterRequestDTO> requestEntity = new HttpEntity<>(request, httpHeaders);

        this.restTemplate.exchange(serverURL + "/carts",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public void updateCart(Long cartId, CartModifyRequestDTO request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-USER-ID", "1");

        HttpEntity<CartModifyRequestDTO> requestEntity = new HttpEntity<>(request, httpHeaders);

        this.restTemplate.exchange(serverURL + "/carts/" + cartId,
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public void deleteCart(Long cartId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-USER-ID", "1");

        HttpEntity<CartModifyRequestDTO> requestEntity = new HttpEntity<>(null, httpHeaders);

        this.restTemplate.exchange(serverURL + "/carts/" + cartId,
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public int calculateTotalCost(List<CartResponseDTO> cart) {
        int total = 0;
        for (CartResponseDTO item : cart) {
            total += item.getUnitCost() * item.getCartQuantity();
        }
        return total;
    }
}