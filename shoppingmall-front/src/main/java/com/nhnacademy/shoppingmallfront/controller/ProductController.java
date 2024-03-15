package com.nhnacademy.shoppingmallfront.controller;

import com.nhnacademy.shoppingmallfront.dto.ProductDetailDTO;
import com.nhnacademy.shoppingmallfront.dto.ProductResponseDTO;
import com.nhnacademy.shoppingmallfront.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "page", required = false) String page){
        int pageNumber = page != null ? Integer.parseInt(page) : 0;
        ProductResponseDTO response = this.productService.getProducts(pageNumber);
        model.addAttribute("page", response);
        model.addAttribute("products", response.getContent());

        return "pages/home";
    }

    @PostMapping("/product")
    public String getProduct(Model model, @RequestParam("product_id") Long productId){
        ProductDetailDTO response = this.productService.getProduct(productId);

        model.addAttribute("productDetail", response);

        return "pages/product";
    }
}
