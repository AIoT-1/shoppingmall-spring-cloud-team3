package com.nhnacademy.gateway.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDeatilService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {


        return null;
    }
}

/*
참고자료
https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/user-details-service.html

 */