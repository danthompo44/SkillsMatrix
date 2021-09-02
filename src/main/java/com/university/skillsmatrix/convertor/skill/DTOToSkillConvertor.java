package com.university.skillsmatrix.convertor.skill;

import com.university.skillsmatrix.convertor.category.DTOToSkillCategoryConvertor;
import com.university.skillsmatrix.convertor.staffSkill.DTOToStaffSkillConvertor;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.entity.Skill;
import org.springframework.stereotype.Component;

@Component
public class DTOToSkillConvertor {
    private final DTOToSkillCategoryConvertor catConvertor = new DTOToSkillCategoryConvertor();
    private final DTOToStaffSkillConvertor staffSkillConvertor = new DTOToStaffSkillConvertor();

    //Only process information needed for viewing all skills in the front end
    public Skill convert(SkillDTO dto){
        Skill skill = new Skill();
        skill.setId(dto.getId());
        skill.setName(dto.getName());
        skill.setCategory(catConvertor.convert(dto.getCategory()));
        staffSkillConvertor.convert(dto.getStaffSkillList());

        return skill;
    }
}
