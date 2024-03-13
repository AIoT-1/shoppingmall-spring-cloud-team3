package com.nhnacademy.shoppingmallfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProductDetailController {

    @GetMapping("/product")
    public String productDetail(){
        return "pages/product_detail";
    }
}
