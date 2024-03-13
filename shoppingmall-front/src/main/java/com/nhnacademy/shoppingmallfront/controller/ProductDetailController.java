package com.nhnacademy.shoppingmallfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/products")
public class ProductDetailController {

    @GetMapping
    public String product(){

        return "pages/product_detail";
    }
}
