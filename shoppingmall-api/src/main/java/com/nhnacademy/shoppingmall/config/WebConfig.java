package com.nhnacademy.shoppingmall.config;

import com.nhnacademy.shoppingmall.converter.StringToAuthConverter;
import com.nhnacademy.shoppingmall.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToAuthConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/api/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.png").addResourceLocations("classpath:/");
        registry.addResourceHandler("/*.jpeg").addResourceLocations("classpath:/");
        registry.addResourceHandler("/*.jpg").addResourceLocations("classpath:/");
    }
}