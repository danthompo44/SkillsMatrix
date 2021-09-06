package com.university.skillsmatrix.convertors.skill;

import com.university.skillsmatrix.convertor.skill.DTOToSkillConvertor;
import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.entity.Skill;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOToSkillConvertorTest {
    private DTOToSkillConvertor convertor;
    private SkillDTO dto;

    @Before
    public void init(){
        convertor = new DTOToSkillConvertor();
        dto = new SkillDTO();
        SkillCategoryDTO catDTO = new SkillCategoryDTO();

        catDTO.setId(1);
        catDTO.setDescription("Software");

        dto.setId(2);
        dto.setName("Java");
        dto.setCategory(catDTO);
    }

    @Test
    public void test01_Convertor_Behaves_As_Expected(){
        Skill skill = convertor.convert(dto);

        assertEquals(dto.getId(), skill.getId());
        assertEquals(dto.getName(), skill.getName());
        assertEquals(dto.getCategory().getId(), skill.getCategory().getId());
        assertEquals(dto.getCategory().getDescription(), skill.getCategory().getDescription());
    }
}
