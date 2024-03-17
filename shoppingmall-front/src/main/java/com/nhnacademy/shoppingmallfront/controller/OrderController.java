package com.nhnacademy.shoppingmallfront.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmallfront.dto.AddressDTO;
import com.nhnacademy.shoppingmallfront.dto.CartResponseDTO;
import com.nhnacademy.shoppingmallfront.dto.OrderRegisterRequestDTO;
import com.nhnacademy.shoppingmallfront.dto.UserDTO;
import com.nhnacademy.shoppingmallfront.service.AddressService;
import com.nhnacademy.shoppingmallfront.service.OrderService;
import com.nhnacademy.shoppingmallfront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @GetMapping("/order")
    public String order(Model model, @RequestParam("cartItems") String cartItems) {
        ObjectMapper objectMapper = new ObjectMapper();
        int totalCost;

        try {
            List<CartResponseDTO> orderItemList = objectMapper.readValue(cartItems, new TypeReference<List<CartResponseDTO>>() {
            });
            totalCost = orderService.calculateTotalCost(orderItemList);

            model.addAttribute("totalCost", totalCost);
            model.addAttribute("orderItems", orderItemList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        UserDTO user = userService.getUser();
        model.addAttribute("user", user);

        List<AddressDTO> address = addressService.getAddress();
        model.addAttribute("address", address);

        return "pages/order_form";
    }

    @PostMapping("/order")
    public String addOrder(@RequestParam("itemIds") String[] itemIds, @RequestParam("addressId") String addressId) {
        List<Integer> intItemIds = Arrays.stream(itemIds)
                .map(s -> s.replaceAll("[\\[\\]\"]", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        OrderRegisterRequestDTO request = new OrderRegisterRequestDTO();
        request.setCartIdList(intItemIds);
        request.setAddressId(Long.parseLong(addressId));

        System.out.println(request.getAddressId());
        orderService.addOrder(request);

        return "redirect:/";
    }

    @GetMapping("/orderList")
    public String orderList(@PathVariable Long loginId){

        return "pages/order_list";
    }

    @GetMapping("/orderDetail")
    public String orderDetail(@PathVariable Long loginId){

        return "pages/order_detail";
    }
}