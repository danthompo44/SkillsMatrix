package com.university.skillsmatrix.web;

import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.service.DetailsService;
import com.university.skillsmatrix.service.ManagerService;
import com.university.skillsmatrix.service.StaffService;
import com.university.skillsmatrix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/details")//localhost:8081/details
@RequiredArgsConstructor
public class DetailsController {
    private final DetailsService detailsService;
    private final UserService userService;
    private final ManagerService managerService;
    private final StaffService staffService;

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public String viewDetails(Model model){
        addDetailsToModel(model);
        return "viewMyDetails";
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('USER')")
    public String updateDetails(@Valid PersonalDetailsDTO dto,
                                BindingResult result,
                                Model model) {
        if(result.hasErrors()) {
            model.addAttribute("errorString", "All fields must have a value");
            addDetailsToModel(model);
            return "viewMyDetails";
        }

        detailsService.save(dto);
        return viewDetails(model);
    }

    public void addDetailsToModel(Model model){
        if(userService.isManager()){
            model.addAttribute("details", managerService.getManagerByAppUserId(userService.getAppUser().getId()).getDetails());
            // Query Manager Service for their details
        } else  {
            model.addAttribute("details", staffService.getStaffByAppUserId(userService.getAppUser().getId()).getDetails());
            //Query Staff Service for their details
        }
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public String deleteDetails(@PathVariable Long id, Model model) {
        detailsService.removeDetails(id);
        return viewDetails(model);
    }
}
