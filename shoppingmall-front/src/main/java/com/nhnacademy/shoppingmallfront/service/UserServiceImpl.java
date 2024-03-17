package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.ProductDetailDTO;
import com.nhnacademy.shoppingmallfront.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final RestTemplate restTemplate;
    private final HttpServletRequest request;

    @Value("${shoppingmall_server_url}")
    private String serverURL;

    public UserServiceImpl(RestTemplate restTemplate, HttpServletRequest request) {
        this.restTemplate = restTemplate;
        this.request = request;
    }

    @Override
    public UserDTO getUser() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        String token = (String) request.getSession().getAttribute("token");
        httpHeaders.set("X-USER-ID", token);

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<UserDTO> response = this.restTemplate.exchange(
                serverURL + "/users/" + 1,
                HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<UserDTO>() {}
        );
        return response.getBody();
    }
}
