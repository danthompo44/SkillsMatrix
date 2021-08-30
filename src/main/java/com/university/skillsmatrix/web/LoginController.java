package com.university.skillsmatrix.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid credentials given");
        }
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin")
    public String adminPage(Model model){
        return "adminHome";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user")
    public String userPage(){
        return "userHome";
    }
}
