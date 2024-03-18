package com.nhnacademy.shoppingmallfront.controller;

import com.nhnacademy.shoppingmallfront.dto.CartModifyRequestDTO;
import com.nhnacademy.shoppingmallfront.dto.CartRegisterRequestDTO;
import com.nhnacademy.shoppingmallfront.dto.CartResponseDTO;
import com.nhnacademy.shoppingmallfront.dto.ProductResponseDTO;
import com.nhnacademy.shoppingmallfront.service.CartService;
import com.nhnacademy.shoppingmallfront.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String getCart(Model model) {
        List<CartResponseDTO> response = this.cartService.getCart();
        int totalCost = cartService.calculateTotalCost(response);

        model.addAttribute("cart", response);
        model.addAttribute("totalCost", totalCost);

        return "pages/cart";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam("quantity") int quantity, @RequestParam("product_id") Long productId, HttpSession session) {
        List<CartResponseDTO> response = this.cartService.getCart();
        for (CartResponseDTO item : response) {
            if(item.getProductId().equals(productId)){
                CartModifyRequestDTO request = new CartModifyRequestDTO();
                request.setQuantity(item.getCartQuantity() + quantity);
                this.cartService.updateCart(item.getId(), request);
            }
        }
        CartRegisterRequestDTO request = new CartRegisterRequestDTO();
        request.setProductId(productId);
        request.setQuantity(quantity);

        this.cartService.addCart(request);

        session.setAttribute("cartItemCount", response.size());

        return "redirect:/cart";
    }

    @PostMapping("/cart/{cartId}")
    public String updateCart(@RequestParam(value = "method", required = false) String method, @RequestParam(value = "quantity", required = false, defaultValue = "0") int quantity, @PathVariable("cartId") Long cartId, HttpSession session) {
        if (method.equals("put")) {
            CartModifyRequestDTO request = new CartModifyRequestDTO();
            request.setQuantity(quantity);

            this.cartService.updateCart(cartId, request);
        } else if (method.equals("delete")) {
            this.cartService.deleteCart(cartId);
        }

        List<CartResponseDTO> response = this.cartService.getCart();
        int cartItemCount = 0;
        if (response != null) {
            cartItemCount = response.size();
        }
        session.setAttribute("cartItemCount", cartItemCount);

        return "redirect:/cart";
    }

}