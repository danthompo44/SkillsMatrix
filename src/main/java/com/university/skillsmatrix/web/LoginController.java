package com.university.skillsmatrix.web;

import com.university.skillsmatrix.service.ManagerService;
import com.university.skillsmatrix.service.StaffService;
import com.university.skillsmatrix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final StaffService staffService;
    private final ManagerService managerService;

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
        String firstName = managerService.getManagerByAppUserId
                (userService.getAppUser().getId()).getDetails().getFirstName();
        model.addAttribute("username", String.format("Welcome %s", firstName));
        return "adminHome";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user")
    public String userPage(Model model){
        String firstName = staffService.getStaffByAppUserId
                (userService.getAppUser().getId()).getDetails().getFirstName();
        model.addAttribute("username", String.format("Welcome %s", firstName));
        return "userHome";
    }
}
