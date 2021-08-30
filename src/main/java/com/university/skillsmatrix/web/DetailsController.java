package com.university.skillsmatrix.web;

import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.service.DetailsService;
import com.university.skillsmatrix.service.ManagerService;
import com.university.skillsmatrix.service.StaffService;
import com.university.skillsmatrix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/details")//localhost:8081/details
@RequiredArgsConstructor
public class DetailsController {
    private final DetailsService detailsService;
    private final UserService userService;
    private final ManagerService managerService;
    private final StaffService staffService;

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public String getDetailsById(Model model){
        System.out.println(userService.isManager());
        if(userService.isManager()){
            System.out.println(detailsService);
            System.out.println(managerService.getManagerByAppUserId(userService.getAppUser().getId()).getDetails().getFirstName());
            // Query Manager Service for their details
        } else  {
            System.out.println(staffService.getStaffByAppUserId(userService.getAppUser().getId()).getDetails().getFirstName());
            //Query Staff Service for their details
        }
//        model.addAttribute("myDetails", detailsService.getDetailsById(userService.getAppUser().get));

        return "viewMyDetails";
    }
}