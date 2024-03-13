package com.nhnacademy.shoppingmallfront.config;

import com.nhnacademy.shoppingmallfront.exception.RestTemplateResponseErrorHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setReadTimeout(Duration.ofSeconds(5L))
                .setConnectTimeout(Duration.ofSeconds(3L))
                .additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
                .rootUri(String.format("http://192.168.71.99:8200/"))
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }
}