package com.university.skillsmatrix.DTO;

import com.university.skillsmatrix.domain.*;
import org.junit.Before;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffSkillDTOValidatorTest {
    private Validator validator;
    private StaffDTO staff;
    private RoleDTO role;
    private SkillDTO skill1;
    private StaffSkillDTO staffSkillDTO;

    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        staff = new StaffDTO();
        staff.setId(1);
        createDetails();
        staffSkillDTO = new StaffSkillDTO();
        staffSkillDTO.setId(67);
    }

    private void createDetails(){
        createPersonalDetails();
        createUserDetails();
        createManagerDetails();
        createSkillDetails();
    }

    private void createPersonalDetails(){
        PersonalDetailsDTO details = new PersonalDetailsDTO();
        details.setId(2);
        details.setFirstName("firstName");
        details.setSurname("surname");
        details.setAddressFirstLine("1st Address Line");
        details.setAddressSecondLine("2nd Address Line");
        details.setCounty("County");
        details.setPostcode("Postcode");
    }

    private void createRoleDetails(){
        role = new RoleDTO();
        role.setId(3);
        role.setType("Manager");
    }

    private void createUserDetails(){
        AppUserDTO user = new AppUserDTO();
        user.setId(4);
        user.setUsername("Username");
        user.setEmail("Email@Email.com");
        user.setPassword("Password");
        createRoleDetails();
        user.setRole(role);
    }

    //Just Set An Id, no need for all info
    private void createManagerDetails(){
        ManagerDTO manager = new ManagerDTO();
        manager.setId(5);
    }

    private void createSkillDetails(){
        skill1 = new SkillDTO();
        skill1.setId(6);
    }

    private void testSkillDetails(StaffSkillDTO dto, boolean isValid){
        setSkillStrength();
        setStaff();
        setDate();
        assertStaffSkill(dto, isValid);
    }

    private void testStaffDetails(StaffSkillDTO dto, boolean isValid){
        setDate();
        setSkillStrength();
        setSkill();
        assertStaffSkill(dto, isValid);
    }

    private void testSkillStrength(StaffSkillDTO dto, boolean isValid){
        setDate();
        setStaff();
        setSkill();
        assertStaffSkill(dto, isValid);
    }

    private void assertStaffSkill(StaffSkillDTO d, boolean isValid){
        Set<ConstraintViolation<StaffSkillDTO>> violations = validator.validate(d);
        assertEquals(violations.isEmpty(), isValid);
    }

    private void setSkillStrength(){
        staffSkillDTO.setSkillStrength(6);
    }

    private void setSkill(){
        staffSkillDTO.setSkill(skill1);
    }

    private void setStaff(){
        staffSkillDTO.setStaff(staff);
    }

    private void setDate(){
        staffSkillDTO.setExpiryDate(new Date());
    }

    @Test
    public void skillStrengthIsNegative(){
        staffSkillDTO.setSkillStrength(-1);
        testSkillStrength(staffSkillDTO, false);
    }

    @Test
    public void skillStrengthIsTooLarge(){
        staffSkillDTO.setSkillStrength(11);
        testSkillStrength(staffSkillDTO, false);
    }

    @Test
    public void skillStrengthIsValid(){
        staffSkillDTO.setSkillStrength(0);
        testSkillStrength(staffSkillDTO, true);
        staffSkillDTO.setSkillStrength(10);
        testSkillStrength(staffSkillDTO, true);
    }

    @Test
    public void skillIsNull(){
        testSkillDetails(staffSkillDTO, false);
    }

    @Test
    public void skillIsInstantiated(){
        staffSkillDTO.setSkill(skill1);
        testSkillDetails(staffSkillDTO, true);
    }

    @Test
    public void staffIsNull(){
        testStaffDetails(staffSkillDTO, false);
    }

    @Test
    public void staffIsInstantiated(){
        staffSkillDTO.setStaff(staff);
        testStaffDetails(staffSkillDTO, true);
    }
}
