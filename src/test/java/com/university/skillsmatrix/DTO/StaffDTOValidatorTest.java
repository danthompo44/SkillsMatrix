package com.university.skillsmatrix.DTO;

import com.university.skillsmatrix.domain.*;
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
public class StaffDTOValidatorTest {
    private Validator validator;
    private StaffDTO staff;
    private PersonalDetailsDTO details;
    private RoleDTO role;
    private AppUserDTO user;
    private ManagerDTO manager;
    private SkillDTO skill1;
    private SkillDTO skill2;

    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        staff = new StaffDTO();
        staff.setId(1);
        createDetails();
    }

    private void createDetails(){
        createPersonalDetails();
        createUserDetails();
        createManagerDetails();
        createSkillDetails();
    }

    private void createPersonalDetails(){
        details = new PersonalDetailsDTO();
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
        user = new AppUserDTO();
        user.setId(4);
        user.setUsername("Username");
        user.setEmail("Email@Email.com");
        user.setPassword("Password");
        createRoleDetails();
        user.setRole(role);
    }

    //Just Set An Id, no need for all info
    private void createManagerDetails(){
        manager = new ManagerDTO();
        manager.setId(5);
    }

    private void createSkillDetails(){
        skill1 = new SkillDTO();
        skill1.setId(6);

        skill2 = new SkillDTO();
        skill2.setId(7);
    }

    private void testPersonalDetails(StaffDTO s, boolean isValid){
        setUserDetails();
        setManagerDetails();
        setSkillList();
        Set<ConstraintViolation<StaffDTO>> violations = validator.validate(s);
        assertEquals(violations.isEmpty(), isValid);
    }

    private void testUserDetails(StaffDTO s, boolean isValid){
        setPersonalDetails();
        setManagerDetails();
        setSkillList();
        Set<ConstraintViolation<StaffDTO>> violations = validator.validate(s);
        assertEquals(violations.isEmpty(), isValid);
    }

    private void testManagerDetails(StaffDTO s, boolean isValid){
        setPersonalDetails();
        setUserDetails();
        setSkillList();
        Set<ConstraintViolation<StaffDTO>> violations = validator.validate(s);
        assertEquals(violations.isEmpty(), isValid);
    }

    private void setPersonalDetails(){
        staff.setDetails(details);
    }

    private void setUserDetails(){
        staff.setUser(user);
    }

    private void setManagerDetails(){
        staff.setManager(manager);
    }

    private void setSkillList(){
        List<SkillDTO> skillList = new ArrayList<>();
        skillList.add(skill1);
        staff.setSkills(skillList);
    }

    @Test
    public void test01PersonalDetailsAreNull(){
        testPersonalDetails(staff, false);
    }
    @Test
    public void test02PersonalDetailsAreInitialised(){
        setPersonalDetails();
        testPersonalDetails(staff, true);
    }
    @Test
    public void test03UserDetailsAreNull(){
        testUserDetails(staff, false);
    }
    @Test
    public void test04UserDetailsAreInitialised(){
        setUserDetails();
        testUserDetails(staff, true);
    }
    @Test
    public void test05ManagerDetailsAreNull(){
        testManagerDetails(staff, false);
    }
    @Test
    public void test06ManagerDetailsAreInitialised(){
        setManagerDetails();
        testManagerDetails(staff, true);
    }
    @Test
    public void test07SkillListCanBeAddedTooAndHoldsExpectedSkills(){
        assertInitialSkillList();
        staff.addSkill(skill2);
        assertEquals(staff.getSkills().size(), 2);
        assertEquals(staff.getSkills().get(0).getId(), skill1.getId());
        assertEquals(staff.getSkills().get(1).getId(), skill2.getId());
    }
    @Test
    public void test08SkillListCanBeRemovedFromAndHoldsExpectedSkills(){
        assertInitialSkillList();
        staff.removeSkill(skill1);
        assertEquals(staff.getSkills().size(), 0);
    }
    private void assertInitialSkillList(){
        setSkillList();
        assertEquals(staff.getSkills().size(), 1);
        assertEquals(staff.getSkills().get(0).getId(), skill1.getId());
    }
}
