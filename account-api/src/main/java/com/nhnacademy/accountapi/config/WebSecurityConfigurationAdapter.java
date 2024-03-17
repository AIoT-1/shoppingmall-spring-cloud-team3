package com.nhnacademy.accountapi.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public abstract class WebSecurityConfigurationAdapter {
    protected AuthenticationManager authenticationManagerBean;

    protected abstract void configure(HttpSecurity http) throws Exception;

    protected abstract void configure(AuthenticationManagerBuilder auth) throws Exception;
}
