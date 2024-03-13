package com.nhnacademy.shoppingmallfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ManageController {
    @GetMapping("/manage")
    public String manage(){
        return "pages/manage_form";
    }
}

