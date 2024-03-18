package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final RestTemplate restTemplate;

    private final HttpServletRequest request;

    @Value("${shoppingmall_server_url}")
    private String serverURL;

    public AddressServiceImpl(RestTemplate restTemplate, HttpServletRequest request) {
        this.restTemplate = restTemplate;
        this.request = request;
    }

    @Override
    public List<AddressDTO> getAddress() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        String token = (String) request.getSession().getAttribute("token");
        httpHeaders.set("X-USER-ID", token);

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<AddressDTO>> response = this.restTemplate.exchange(
                serverURL + "/address",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<AddressDTO>>() {}
        );

        return response.getBody();
    }
}
