package com.nhnacademy.shoppingmallfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class OrderController {
    @GetMapping("/order")
    public String order(){
        return "pages/order_form";
    }

    @GetMapping("/{loginId}/order")
    public String orderList(@PathVariable Long loginId){
        return "pages/order_list";
    }
}