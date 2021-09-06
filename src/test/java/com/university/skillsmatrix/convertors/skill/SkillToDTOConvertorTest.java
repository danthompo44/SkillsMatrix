package com.university.skillsmatrix.convertors.skill;

import com.university.skillsmatrix.convertor.skill.SkillToDTOConvertor;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.entity.Skill;
import com.university.skillsmatrix.entity.SkillCategory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkillToDTOConvertorTest {
    private SkillToDTOConvertor convertor;
    private Skill skill;

    @Before
    public void init(){
        convertor = new SkillToDTOConvertor();
        skill = new Skill();
        SkillCategory cat = new SkillCategory();

        cat.setId(2);
        cat.setDescription("Software");

        skill.setId(1);
        skill.setName("Java");
        skill.setCategory(cat);
    }

    @Test
    public void test01_Convertor_Successfully_Converts_Skill_To_SkillDTO(){
        SkillDTO dto = convertor.convert(skill);

        assertEquals(skill.getId(), dto.getId());
        assertEquals(skill.getName(), dto.getName());

        assertEquals(skill.getCategory().getId(), dto.getCategory().getId());
        assertEquals(skill.getCategory().getDescription(), dto.getCategory().getDescription());
    }
}
