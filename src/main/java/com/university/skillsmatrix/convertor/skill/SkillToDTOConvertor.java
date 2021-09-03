package com.university.skillsmatrix.convertor.skill;

import com.university.skillsmatrix.convertor.category.SkillCategoryToDTOConvertor;
import com.university.skillsmatrix.convertor.staffSkill.StaffSkillToDTOConvertor;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.entity.Skill;
import com.university.skillsmatrix.entity.StaffSkill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillToDTOConvertor {
    private final SkillCategoryToDTOConvertor catConvertor = new SkillCategoryToDTOConvertor();

    //Only process information needed for viewing all skills in the front end
    public SkillDTO convert(Skill s){
        SkillDTO dto = new SkillDTO();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setCategory(catConvertor.convert(s.getCategory()));

        return dto;
    }
}
