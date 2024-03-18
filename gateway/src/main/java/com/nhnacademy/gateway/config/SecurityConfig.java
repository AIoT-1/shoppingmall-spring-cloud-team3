package com.nhnacademy.gateway.config;

import com.nhnacademy.gateway.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService customUserDetailService() {
        return new CustomUserDetailService();
    }
}