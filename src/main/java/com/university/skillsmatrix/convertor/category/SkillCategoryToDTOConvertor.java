package com.university.skillsmatrix.convertor.category;

import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.entity.SkillCategory;
import org.springframework.stereotype.Component;

@Component
public class SkillCategoryToDTOConvertor {
    public SkillCategoryDTO convert(SkillCategory category){
        SkillCategoryDTO dto = new SkillCategoryDTO();
        dto.setId(category.getId());
        dto.setDescription(category.getDescription());

        return dto;
    }
}
