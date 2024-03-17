package com.nhnacademy.shoppingmallfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/login")
public class LoginController {
//    @Autowired
//    private UserService userService;

    @GetMapping
    public String login(){
        return "pages/login_form";
    }

    @PostMapping
    public String doLogin(Model model, @RequestParam("user_id") String userId, @RequestParam("user_password") String password, HttpSession session){
        String token = "1";

        session.setAttribute("token", token);

        return "redirect:/";
    }


//    @PostMapping
//    public String index(Model model, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "keyword", required = false) String keyword){
//        int pageNumber = page != null && !page.isEmpty() ? Integer.parseInt(page) : 0;
//        int categoryId = category != null && !category.isEmpty() ? Integer.parseInt(category) : 0;
//        ProductResponseDTO content;
//
//        if (keyword != null && !keyword.isEmpty()) {
//            content = this.productService.getProducts(pageNumber, categoryId, keyword);
//        } else {
//            content = this.productService.getProducts(pageNumber, categoryId, null);
//        }
//        model.addAttribute("page", content);
//        model.addAttribute("products", content.getContent());
//
//        CategoryResponseDTO categories = this.categoryService.getCategories();
//        model.addAttribute("categories", categories.getCategories());
//
//        if(categoryId > 0) {
//            model.addAttribute("selectedCategory", categoryId);
//        }
//
//        return "pages/home";
//    }
}
