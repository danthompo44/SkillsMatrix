package com.university.skillsmatrix.web;

import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.service.CategoryService;
import com.university.skillsmatrix.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skill")//localhost:8081/skills
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;
    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public String getAllSkills(Model model){
        model.addAttribute("skillList", skillService.getAllSkills());
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
}
