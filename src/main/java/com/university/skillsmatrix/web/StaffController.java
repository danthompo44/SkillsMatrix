package com.university.skillsmatrix.web;

import com.university.skillsmatrix.service.ManagerService;
import com.university.skillsmatrix.service.SkillService;
import com.university.skillsmatrix.service.StaffService;
import com.university.skillsmatrix.service.StaffSkillService;
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
    private final StaffSkillService staffSkillService;
    private final ManagerService managerService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public String getAllStaff(Model model){
        model.addAttribute("staffList",
                staffService.getAllStaff());
        return "viewAllStaff";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/skillPage/{id}")
    public String viewAStaffMembersSkillsPage(
            @PathVariable Long id, Model model){
        model.addAttribute("staffSkills",
                staffSkillService
                        .getStaffSkillsByStaffId(id));
        model.addAttribute("title",
                String.format("%s's Skills",
                        staffService.getStaffById(id).
                                getDetails().getFullName()));
        return "viewSkillsByStaff";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/managerPage/{id}")
    public String viewAManagersStaffPage(@PathVariable Long id, Model model){
        try{
            model.addAttribute("staffList",
                    staffService.getStaffByManagerId(id));
            model.addAttribute("title",
                    String.format("%s's Staff",
                            managerService.findByManagerId(id).getDetails().getFullName()));
        } catch (Exception ex){
            return "error";
        }
        return "viewManagersStaff";
    }
}
