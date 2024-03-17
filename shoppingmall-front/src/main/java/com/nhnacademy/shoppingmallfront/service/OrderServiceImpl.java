package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.CartResponseDTO;
import com.nhnacademy.shoppingmallfront.dto.OrderRegisterRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final RestTemplate restTemplate;
    private final HttpServletRequest httpServletRequest;

    @Value("${shoppingmall_server_url}")
    private String serverURL;

    public OrderServiceImpl(RestTemplate restTemplate, HttpServletRequest request) {
        this.restTemplate = restTemplate;
        this.httpServletRequest = request;
    }

    @Override
    public void addOrder(OrderRegisterRequestDTO request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        String token = (String) httpServletRequest.getSession().getAttribute("token");
        httpHeaders.set("X-USER-ID", token);

        HttpEntity<OrderRegisterRequestDTO> requestEntity = new HttpEntity<>(request, httpHeaders);

        this.restTemplate.exchange(serverURL + "/orders",
                HttpMethod.POST,
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
