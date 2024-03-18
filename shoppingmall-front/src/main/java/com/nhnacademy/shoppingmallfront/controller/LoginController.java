package com.nhnacademy.shoppingmallfront.controller;

import com.nhnacademy.shoppingmallfront.dto.CartResponseDTO;
import com.nhnacademy.shoppingmallfront.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    CartService cartService;

    @GetMapping
    public String login(){
        return "pages/login_form";
    }

    @PostMapping
    public String doLogin(Model model, @RequestParam("user_id") String userId, @RequestParam("user_password") String password, HttpSession session){
        String token = "1";

        session.setAttribute("token", token);
        session.setAttribute("userId", userId);

        List<CartResponseDTO> response = this.cartService.getCart();
        int cartItemCount = 0;
        if (response != null) {
            cartItemCount = response.size();
        }
        session.setAttribute("cartItemCount", cartItemCount);

        return "redirect:/";
    }
}
