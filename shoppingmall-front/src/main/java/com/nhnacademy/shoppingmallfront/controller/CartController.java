package com.nhnacademy.shoppingmallfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    @GetMapping("/cart")
    public String cart(){
        return "pages/cart";
    }
}