package com.university.skillsmatrix.web;

import com.university.skillsmatrix.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skills")//localhost:8081/skills
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @GetMapping("/admin/all")
    public String getAllSkills(Model model){

        System.out.println("Get All SKills");
        model.addAttribute("skillList", skillService.getAllSkills());
        return "viewAllSkills";
    }
}
