package com.nhnacademy.shoppingmallfront.controller;

import com.nhnacademy.shoppingmallfront.dto.ProductDetailDTO;
import com.nhnacademy.shoppingmallfront.dto.ProductImageDTO;
import com.nhnacademy.shoppingmallfront.dto.ReviewDTO;
import com.nhnacademy.shoppingmallfront.service.ProductService;
import com.nhnacademy.shoppingmallfront.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/product")
    public String getProduct(Model model, @RequestParam("product_id") Long productId){
        ProductDetailDTO productDetail = this.productService.getProduct(productId);
        model.addAttribute("productDetail", productDetail);

        List<ReviewDTO> reviews = this.reviewService.getReviews(Integer.parseInt(String.valueOf(productId)));
        Collections.sort(reviews, (r1, r2) -> r2.getCreatedAt().compareTo(r1.getCreatedAt()));
        model.addAttribute("reviews", reviews);

        List<ProductImageDTO> images = this.productService.getImages(Integer.parseInt(String.valueOf(productId)));
        model.addAttribute("images", images);
        return "pages/product_detail";
    }
}
