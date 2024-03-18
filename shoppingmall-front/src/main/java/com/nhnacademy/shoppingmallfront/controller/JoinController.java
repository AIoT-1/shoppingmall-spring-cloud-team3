package com.nhnacademy.shoppingmallfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinController {
    @GetMapping("/join")
    public String join(){
        return "pages/join_form";
    }
}

