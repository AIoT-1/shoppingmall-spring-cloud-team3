package com.nhnacademy.shoppingmallfront.controller;

import com.nhnacademy.shoppingmallfront.dto.CategoryDTO;
import com.nhnacademy.shoppingmallfront.dto.ProductResponseDTO;
import com.nhnacademy.shoppingmallfront.service.CategoryService;
import com.nhnacademy.shoppingmallfront.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "keyword", required = false) String keyword, HttpSession session){
        int pageNumber = page != null && !page.isEmpty() ? Integer.parseInt(page) : 0;
        int categoryId = category != null && !category.isEmpty() ? Integer.parseInt(category) : 0;
        ProductResponseDTO content;

        if (keyword != null && !keyword.isEmpty()) {
            content = this.productService.getProducts(pageNumber, categoryId, keyword);
        } else {
            content = this.productService.getProducts(pageNumber, categoryId, null);
        }
        model.addAttribute("page", content);
        model.addAttribute("products", content.getContent());

        List<CategoryDTO> categories = this.categoryService.getCategories();
        model.addAttribute("categories", categories);

        if(categoryId > 0) {
            model.addAttribute("selectedCategory", categoryId);
        }
        return "pages/home";
    }
}
