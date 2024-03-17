package com.nhnacademy.shoppingmallfront.controller;

import com.nhnacademy.shoppingmallfront.dto.OrderRegisterRequestDTO;
import com.nhnacademy.shoppingmallfront.service.OrderService;
import com.nhnacademy.shoppingmallfront.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String order(){
        return "pages/order_form";
    }

    @GetMapping("/orderList")
    public String orderList(@PathVariable Long loginId){

        return "pages/order_list";
    }

    @GetMapping("/orderDetail")
    public String orderDetail(@PathVariable Long loginId){

        return "pages/order_detail";
    }

    @PostMapping("/order")
    public String addOrder(@RequestParam("itemIds") String[] itemIds) {
        List<Integer> intItemIds = Arrays.stream(itemIds)
                .map(s -> s.replaceAll("[\\[\\]\"]", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        OrderRegisterRequestDTO request = new OrderRegisterRequestDTO();
        request.setCartIdList(intItemIds);
        System.out.println(request.getCartIdList());
        System.out.println(request);
        orderService.addOrder(request);

        return "redirect:/order";
    }
}