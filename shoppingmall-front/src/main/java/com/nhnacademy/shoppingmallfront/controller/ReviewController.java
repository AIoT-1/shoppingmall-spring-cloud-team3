package com.nhnacademy.shoppingmallfront.controller;


import com.nhnacademy.shoppingmallfront.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public String addReview(@RequestParam("product_id") String productId,
                            @RequestParam("ratingInput") String rating,
                            @RequestParam("comment") String comment,
                            RedirectAttributes redirectAttributes){
        System.out.println(productId);
        System.out.println(rating);
        System.out.println(comment);

        reviewService.addReview(Integer.parseInt(rating), comment, Integer.parseInt(productId));

        redirectAttributes.addAttribute("product_id", productId);
        return "redirect:/product";
    }

    @PostMapping("/delete")
    public String deleteReview(@RequestParam("review_id") String reviewId,
                               @RequestParam("product_id") String productId,
                               RedirectAttributes redirectAttributes) {
        reviewService.deleteReview(Integer.parseInt(reviewId), Integer.parseInt(productId));

        redirectAttributes.addAttribute("product_id", productId);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateReview(@RequestParam("rating") String rating,
                               @RequestParam("comment") String comment,
                               @RequestParam("review_id") String reviewId,
                               @RequestParam("product_id") String productId,
                               RedirectAttributes redirectAttributes) {
        reviewService.updateReview(Integer.parseInt(rating), comment, Integer.parseInt(reviewId), Integer.parseInt(productId));
        redirectAttributes.addAttribute("product_id", productId);
        return "redirect:/product";
    }
}