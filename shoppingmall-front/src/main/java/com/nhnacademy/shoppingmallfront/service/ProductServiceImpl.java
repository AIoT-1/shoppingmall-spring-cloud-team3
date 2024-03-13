package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final RestTemplate restTemplate;

    @Value("${shoppingmall_server_url}")
    private String serverURL;

    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductResponseDTO getProducts(int page) {
        String url = "";
        if(page > 0) {
            url = serverURL + "/products?page=" + page;
        } else {
            url = serverURL + "/products";
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<ProductResponseDTO> products = this.restTemplate.exchange( url, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<ProductResponseDTO>() {}
        );
        return products.getBody();
    }
}
