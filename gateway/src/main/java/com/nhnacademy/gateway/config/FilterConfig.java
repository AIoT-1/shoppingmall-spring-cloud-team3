package com.nhnacademy.gateway.config;

import com.nhnacademy.gateway.filter.CustomGlobalFilter;
import com.nhnacademy.gateway.filter.JwtAuthorizationHeaderFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class FilterConfig {

    //TODO#2-3 global filter를 bean으로 등록합니다.
    @Bean
    public GlobalFilter customFilter() {
        return new CustomGlobalFilter();
    }

    // JwtAuthorizationHeaderFilter 빈 등록
    @Bean
    public JwtAuthorizationHeaderFilter jwtAuthorizationHeaderFilter() {
        return new JwtAuthorizationHeaderFilter();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



}
