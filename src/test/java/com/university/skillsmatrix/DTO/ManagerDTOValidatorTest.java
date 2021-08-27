package com.university.skillsmatrix.DTO;

import com.university.skillsmatrix.domain.AppUserDTO;
import com.university.skillsmatrix.domain.ManagerDTO;
import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import com.university.skillsmatrix.domain.RoleDTO;
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
public class ManagerDTOValidatorTest {
    private Validator validator;
    private ManagerDTO man;
    private PersonalDetailsDTO details;
    private AppUserDTO user;
    private RoleDTO role;

    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        man  = new ManagerDTO();
        man.setId(1);
        createPersonalDetails();
        createUserDetails();
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

    private void testPersonalDetails(ManagerDTO m, boolean isValid){
        man.setUser(user);
        Set<ConstraintViolation<ManagerDTO>> violations = validator.validate(m);
        assertEquals(violations.isEmpty(), isValid);
    }

    private void testUser(ManagerDTO m, boolean isValid){
        man.setDetails(details);
        Set<ConstraintViolation<ManagerDTO>> violations = validator.validate(m);
        assertEquals(violations.isEmpty(), isValid);
    }

    @Test
    public void test01PersonalDetailsAreNull(){
        testPersonalDetails(man, false);
    }
    @Test
    public void test02PersonalDetailsAreInitialised(){
        man.setDetails(details);
        testPersonalDetails(man, true);
    }
    @Test
    public void test03UserIsNull(){
        testUser(man, false);
    }
    @Test
    public void test04UserIsInitialised(){
        man.setUser(user);
        testUser(man, true);
    }
}
