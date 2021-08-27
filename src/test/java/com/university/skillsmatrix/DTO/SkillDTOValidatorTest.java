package com.university.skillsmatrix.DTO;

import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.domain.StaffDTO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SkillDTOValidatorTest {
    private Validator validator;
    private SkillDTO skill;
    private SkillCategoryDTO cat;
    private List<StaffDTO> staffList;
    private StaffDTO staff1;
    private StaffDTO staff2;

    private final String tooSmallString = "";
    private final String tooLargeString = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    private final String validString = "Valid";

    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        skill = new SkillDTO();
        skill.setId(1);
        createCategoryDetails();
        initialiseStaffList();
    }

    private void createCategoryDetails(){
        cat = new SkillCategoryDTO();
        cat.setId(1);
        cat.setDescription("description");
    }

    private void testName(SkillDTO s, boolean isValid){
        s.setCategory(cat);
        Set<ConstraintViolation<SkillDTO>> violations = validator.validate(s);
        assertEquals(violations.isEmpty(), isValid);
    }

    private void testCategory(SkillDTO s, boolean isValid){
        s.setName(validString);
        Set<ConstraintViolation<SkillDTO>> violations = validator.validate(s);
        assertEquals(violations.isEmpty(), isValid);
    }

    private void initialiseStaffList(){
        staffList = new ArrayList<>();
        createStaff();
        staffList.add(staff1);
        skill.setStaffList(staffList);
    }

    private void createStaff(){
        //Create Staff but with no info other than Ids for assertions
        staff1 = new StaffDTO();
        staff2 = new StaffDTO();

        staff1.setId(1);
        staff2.setId(2);
    }

    @Test
    public void test01NameIsTooLarge(){
        skill.setName(tooLargeString);
        testName(skill, false);
    }
    @Test
    public void test02NameIsTooSmall(){
        skill.setName(tooSmallString);
        testName(skill, false);
    }
    @Test
    public void test03NameIsNull(){
        testName(skill, false);
    }
    @Test
    public void test04NameIsValid(){
        skill.setName(validString);
        testName(skill, true);
    }
    @Test
    public void test05CategoryIsNull(){
        testCategory(skill, false);
    }
    @Test
    public void test06CategoryIsInitialised(){
        skill.setCategory(cat);
        testCategory(skill, true);
    }
    @Test
    public void test07AbleToAddStaffToASkill(){
        assertEquals(skill.getStaffList().size(), 1);
        assertEquals(skill.getStaffList().get(0).getId(), staff1.getId());
        skill.addStaff(staff2);
        assertEquals(skill.getStaffList().size(), 2);
        assertEquals(skill.getStaffList().get(0).getId(), staff1.getId());
        assertEquals(skill.getStaffList().get(1).getId(), staff2.getId());
    }
    @Test
    public void test08AbleToRemoveStaffFromASkill(){
        assertEquals(skill.getStaffList().size(), 1);
        assertEquals(skill.getStaffList().get(0).getId(), staff1.getId());
        skill.removeStaff(staff1);
        assertEquals(skill.getStaffList().size(), 0);
    }
}
