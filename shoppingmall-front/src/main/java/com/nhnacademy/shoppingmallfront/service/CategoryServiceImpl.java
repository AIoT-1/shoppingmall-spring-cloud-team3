package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final RestTemplate restTemplate;
    private final HttpServletRequest request;

    @Value("${shoppingmall_server_url}")
    private String serverURL;

    public CategoryServiceImpl(RestTemplate restTemplate, HttpServletRequest request) {
        this.restTemplate = restTemplate;
        this.request = request;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        String token = (String) request.getSession().getAttribute("token");
        httpHeaders.set("X-USER-ID", token);

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<CategoryDTO>> response = this.restTemplate.exchange(
                serverURL + "/categories",
                HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<List<CategoryDTO>>() {}
        );

        return response.getBody();
    }
}

