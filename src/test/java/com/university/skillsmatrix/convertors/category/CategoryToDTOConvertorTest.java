package com.university.skillsmatrix.convertors.category;

import com.university.skillsmatrix.convertor.category.SkillCategoryToDTOConvertor;
import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.entity.SkillCategory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryToDTOConvertorTest {
    private SkillCategoryToDTOConvertor catConvertor;
    private SkillCategory category;
    private SkillCategoryDTO categoryDTO;

    @Before
    public void init() {
        catConvertor = new SkillCategoryToDTOConvertor();
        category = new SkillCategory();
        categoryDTO = new SkillCategoryDTO();

        category.setId(1);
        category.setDescription("A Category");
    }

    @Test
    public void test01WhenGivenACategoryConvertorReturnsCategoryDTO(){
        categoryDTO = catConvertor.convert(category);
        assertEquals(category.getId(), categoryDTO.getId());
        assertEquals(category.getDescription(), categoryDTO.getDescription());
    }

}
