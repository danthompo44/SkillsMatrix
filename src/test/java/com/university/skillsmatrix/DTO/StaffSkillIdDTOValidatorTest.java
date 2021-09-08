package com.university.skillsmatrix.DTO;

import com.university.skillsmatrix.domain.StaffSkillIdDTO;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffSkillIdDTOValidatorTest {
    private Validator validator;
    private StaffSkillIdDTO staffSkillIdDTO;

    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        staffSkillIdDTO = new StaffSkillIdDTO();
        staffSkillIdDTO.setId(1);
    }

    private void testSkillStrength(StaffSkillIdDTO dto, boolean isValid){
        setDate();
        setSkillId();
        setStaffId();
        assertStaffSkill(dto, isValid);
    }

    private void testSkillId(StaffSkillIdDTO dto, boolean isVaild){
        setDate();
        setSkillStrength();
        setStaffId();
        assertStaffSkill(dto, isVaild);
    }

    private void testStaffId(StaffSkillIdDTO dto, boolean isValid){
        setDate();
        setSkillStrength();
        setSkillId();
        assertStaffSkill(dto, isValid);
    }

    private void assertStaffSkill(StaffSkillIdDTO dto, boolean isValid){
        Set<ConstraintViolation<StaffSkillIdDTO>> violations = validator.validate(dto);
        assertEquals(violations.isEmpty(), isValid);
    }

    private void setDate(){
        staffSkillIdDTO.setExpiryDate(new Date());
    }

    private void setSkillId(){
        staffSkillIdDTO.setSkillId(23L);
    }

    private void setStaffId(){
        staffSkillIdDTO.setStaffId(34L);
    }

    private void setSkillStrength(){
        staffSkillIdDTO.setSkillStrength(6);
    }

    @Test
    public void skillStrengthIsNegative(){
        staffSkillIdDTO.setSkillStrength(-1);
        testSkillStrength(staffSkillIdDTO, false);
    }

    @Test
    public void skillStrengthIsTooLarge(){
        staffSkillIdDTO.setSkillStrength(11);
        testSkillStrength(staffSkillIdDTO, false);
    }

    @Test
    public void skillStrengthIsValid(){
        staffSkillIdDTO.setSkillStrength(10);
        testSkillStrength(staffSkillIdDTO, true);
        staffSkillIdDTO.setSkillStrength(0);
        testSkillStrength(staffSkillIdDTO, true);
    }

    @Test
    public void skillIdIsNull(){
        testSkillId(staffSkillIdDTO, false);
    }

    @Test
    public void skillIdIsInitialised(){
        staffSkillIdDTO.setSkillId(4L);
        testSkillId(staffSkillIdDTO, true);
    }

    @Test
    public void staffIdIsNull(){
        testStaffId(staffSkillIdDTO, false);
    }

    @Test
    public void staffIdIsInitialised(){
        staffSkillIdDTO.setStaffId(4L);
        testStaffId(staffSkillIdDTO, true);
    }
}
