package com.university.skillsmatrix.web;

import com.university.skillsmatrix.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")//localhost:8081/staff
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public String getAllStaff(Model model){
        model.addAttribute("staffList", staffService.getAllStaff());
        return "viewAllStaff";
    }
}
