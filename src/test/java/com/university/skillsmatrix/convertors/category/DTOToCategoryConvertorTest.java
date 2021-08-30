package com.university.skillsmatrix.convertors.category;

import com.university.skillsmatrix.convertor.category.DTOToSkillCategoryConvertor;
import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.entity.SkillCategory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DTOToCategoryConvertorTest {
    private DTOToSkillCategoryConvertor catConvertor;
    private SkillCategory category;
    private SkillCategoryDTO categoryDTO;

    @Before
    public void init(){
        catConvertor = new DTOToSkillCategoryConvertor();
        category = new SkillCategory();
        categoryDTO = new SkillCategoryDTO();

        categoryDTO.setId(1);
        categoryDTO.setDescription("A Category");
    }

    @Test
    public void test01WhenGivenACategoryDTOConvertorReturnsACategory(){
        category = catConvertor.convert(categoryDTO);
        assertEquals(categoryDTO.getId(), category.getId());
        assertEquals(categoryDTO.getDescription(), category.getDescription());
    }
}
