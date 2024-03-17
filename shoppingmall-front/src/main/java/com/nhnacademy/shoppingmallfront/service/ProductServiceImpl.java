package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.ProductDetailDTO;
import com.nhnacademy.shoppingmallfront.dto.ProductImageDTO;
import com.nhnacademy.shoppingmallfront.dto.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final RestTemplate restTemplate;
    private final HttpServletRequest request;

    @Value("${shoppingmall_server_url}")
    private String serverURL;

    public ProductServiceImpl(RestTemplate restTemplate, HttpServletRequest request) {
        this.restTemplate = restTemplate;
        this.request = request;
    }

    @Override
    public ProductResponseDTO getProducts(int page, int categoryId, String keyword) {

        String params = "/products?page=" + page;

        if (categoryId > 0) {
            params += "&category=" + categoryId;
        }

        if (keyword != null && !keyword.isEmpty()) {
            params += "&keyword=" + keyword;
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        String token = (String) request.getSession().getAttribute("token");
        httpHeaders.set("X-USER-ID", token);

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<ProductResponseDTO> response = this.restTemplate.exchange(
                serverURL + params,
                HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<ProductResponseDTO>() {}
        );
        return response.getBody();
    }

    @Override
    public ProductDetailDTO getProduct(Long productId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        String token = (String) request.getSession().getAttribute("token");
        httpHeaders.set("X-USER-ID", token);

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<ProductDetailDTO> response = this.restTemplate.exchange(
                serverURL + "/products/" + productId,
                HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<ProductDetailDTO>() {}
        );
        return response.getBody();
    }

    @Override
    public List<ProductImageDTO> getImages(int productId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        String token = (String) request.getSession().getAttribute("token");
        httpHeaders.set("X-USER-ID", token);

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<ProductImageDTO>> response = this.restTemplate.exchange(
                serverURL + "/products/" + productId + "/images",
                HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<List<ProductImageDTO>>() {}
        );
        return response.getBody();
    }
}
