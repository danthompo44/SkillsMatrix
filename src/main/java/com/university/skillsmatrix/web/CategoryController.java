package com.university.skillsmatrix.web;

import com.university.skillsmatrix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")//localhost:8081/category
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/admin/all")
    public String getAllCategories(Model model){
        model.addAttribute("categoryList", categoryService.getAllCategories());
        return "viewAllCategories";
    }
}
