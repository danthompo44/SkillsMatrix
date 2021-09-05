package com.university.skillsmatrix.web;

import com.university.skillsmatrix.convertor.staffSkill.IdDTOToStaffSkillDTO;
import com.university.skillsmatrix.domain.*;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.service.*;
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
@RequestMapping("/skill")//localhost:8081/skill
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;
    private final CategoryService categoryService;
    private final StaffService staffService;
    private final UserService userService;
    private final StaffSkillService staffSkillService;
    private final IdDTOToStaffSkillDTO idConvertor;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public String getAllSkills(Model model){
        model.addAttribute("skillList", skillService.getAllSkills());
        model.addAttribute("skillCategory", categoryService.getAllCategories());
        model.addAttribute("skillDTO", new SkillDTO());
        return "viewAllSkills";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/editPage/{id}")
    public String editPage(@PathVariable Long id,
                           Model model) {
        try{
            model.addAttribute("skill", skillService.getSkillById(id));
            model.addAttribute("skillCategory", categoryService.getAllCategories());
        } catch (ResourceNotFoundException ex) {
            return "error";
        }

        return "viewASkill";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/update/{id}")
    public String updateSkill(SkillDTO dto,
                              BindingResult result,
                              Model model) {
        if(result.hasErrors()){
            model.addAttribute("skill", dto);
            return "viewASkill";
        }
        skillService.save(dto);
        return getAllSkills(model);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/delete/{id}")
    public String deleteSkill(@PathVariable Long id, Model model){
        try {
            skillService.deleteSkillById(id);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("deletionError", "Unable to delete record");
        }

        return getAllSkills(model);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add")
    public String insertSkill(@Valid SkillDTO skillDTO,
                                 BindingResult result,
                                 Model model) {
        try{
            //Set the description before saving to the repo so UI can see description
            skillDTO.getCategory().setDescription(categoryService.getCategoryById
                    (skillDTO.getCategory().getId()).getDescription());
            skillService.save(skillDTO);
        } catch (Exception ex) {
            model.addAttribute("error", "A skill must have a name");
        }
        return getAllSkills(model);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/staffPage/{id}")
    public String getStaffBySkillId(@PathVariable Long id, Model model){
        try {
            model.addAttribute("staffList", staffService.getStaffBySkillId(id));
            model.addAttribute("skillTitle",
                    String.format("%s Staff", skillService.getSkillById(id).getName()));
        } catch (Exception e){
            return "error";
        }
        return "viewStaffBySkill";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/view")
    public String viewMySkills(Model model){
        model.addAttribute("staffSkillList",
                staffSkillService.getStaffSkillsByStaffId(
                        staffService.getStaffByAppUserId(userService.getAppUser().getId()).getId()));
        return "viewMySkills";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editSkillPage/{id}")
    public String viewStaffSkillEditPage(@PathVariable Long id, Model model){
        try{
            model.addAttribute("staffSkillIdDTO", staffSkillService.findIdDtoById(id));
            model.addAttribute("titleString",
                    String.format("Edit Your %s Skill",
                            skillService.getSkillById(staffSkillService.findById(id).getSkill().getId()).getName()));
        } catch (Exception ex){
            return "error";
        }
        return "editStaffSkill";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/staff/update/{id}")
    public String updateAStaffSkill(StaffSkillIdDTO dto, @PathVariable Long id, Model model){
        if(dto.getSkillStrength() > 10 || dto.getSkillStrength() < 0){
            model.addAttribute("error", "Skill level must be between 0-10.");
            return viewStaffSkillEditPage(id, model);
        }
        try{
            StaffDTO staff = staffService.getStaffById(dto.getStaffId());
            SkillDTO skill = skillService.getSkillById(dto.getSkillId());

            StaffSkillDTO skillDTO = idConvertor.convert(dto, staff, skill);
            staffSkillService.save(skillDTO);
        } catch(Exception ex){
            return "error";
        }
        return viewMySkills(model);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/staff/delete/{id}")
    public String deleteAStaffSkill(@PathVariable Long id, Model model){
        try {
            staffSkillService.deleteStaffSKillById(id);
        } catch (DataIntegrityViolationException ex) { //Catch if deletion fails due to constraint
            model.addAttribute("deletionError", "Category is bound to a skill and cannot be deleted");
        }

        return viewMySkills(model);
    }
}
