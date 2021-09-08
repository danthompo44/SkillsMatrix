package com.university.skillsmatrix.DTO;

import com.university.skillsmatrix.domain.SkillCategoryDTO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SkillCategoryDTOValidatorTest {
    private Validator validator;
    private SkillCategoryDTO cat;

    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        cat  = new SkillCategoryDTO();
        cat.setId(1);
    }

    private void testDescription(SkillCategoryDTO s, boolean isValid){
        Set<ConstraintViolation<SkillCategoryDTO>> violations = validator.validate(s);
        assertEquals(violations.isEmpty(), isValid);
    }

    @Test
    public void test01DescriptionIsTooLarge(){
        String tooLargeString = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        cat.setDescription(tooLargeString);
        testDescription(cat, false);
    }
    @Test
    public void test02DescriptionIsTooSmall(){
        String tooSmallString = "";
        cat.setDescription(tooSmallString);
        testDescription(cat, false);
    }
    @Test
    public void test03DescriptionIsNull(){
        testDescription(cat, false);
    }
    @Test
    public void test04DescriptionIsValid(){
        String validString = "Valid";
        cat.setDescription(validString);
        testDescription(cat,true);
    }

}
