package com.nhnacademy.shoppingmallfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String login(){
        return "pages/login_form";
    }

//    @PostMapping
//    public String doLogin(@ModelAttribute Member member) {
//        restTemplate.postForEntity(
//                BASE_URL,
//                member,
//                Member.class
//        );
//
//        return "redirect:/home";
//    }

//    @PutMapping("/updateBook/{id}")
//    public String updateBook(@PathVariable("id") String id, @ModelAttribute Member member) {
//        restTemplate.put(
//                BASE_URL + "/" + id,
//                member
//        );
//
//        return "redirect:/mypage";
//    }
}
