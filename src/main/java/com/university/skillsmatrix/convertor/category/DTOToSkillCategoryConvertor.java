package com.university.skillsmatrix.convertor.category;

import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.entity.SkillCategory;
import org.springframework.stereotype.Component;

@Component
public class DTOToSkillCategoryConvertor{
    public SkillCategory convert(SkillCategoryDTO dto){
        SkillCategory category = new SkillCategory();
        category.setId(dto.getId());
        category.setDescription(dto.getDescription());

        return category;
    }
}
