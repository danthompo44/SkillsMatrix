package com.university.skillsmatrix.web;

import com.university.skillsmatrix.service.SkillService;
import com.university.skillsmatrix.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")//localhost:8081/staff
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;
    private final SkillService skillService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public String getAllStaff(Model model){
        model.addAttribute("staffList", staffService.getAllStaff());
        return "viewAllStaff";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/skillPage/{id}")
    public String getStaffBySkillId(@PathVariable Long id, Model model){
        System.out.println("Get Staff By Id");
        try {
            model.addAttribute("staffList", staffService.getStaffBySkillId(id));
            model.addAttribute("skillTitle",
                    String.format("%s Staff", skillService.getSkillById(id).getName()));
            System.out.println("No Error");
        } catch (Exception e){
            System.out.println("An Error");
            return "error";
        }
        System.out.println("View Staff By Skill");
        return "viewStaffBySkill";
    }
}
