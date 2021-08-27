package com.university.skillsmatrix.DTO;

import com.university.skillsmatrix.domain.AppUserDTO;
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
public class AppUserDTOValidatorTest {
    private Validator validator;
    private AppUserDTO user;
    private RoleDTO role;

    private final String tooSmallString = "";
    private final String tooLargeString = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    private final String validString = "Valid";

    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        user  = new AppUserDTO();
        user.setId(1);
        role = new RoleDTO();
    }

    private void setRoleDetails(){
        role.setId(2);
        role.setType("Manager");
        user.setRole(role);
    }

    private void testUsername(AppUserDTO u, boolean isValid){
        user.setPassword("password");
        user.setEmail("email@email.com");
        setRoleDetails();
        assertUserDetails(u, isValid);
    }

    private void testPassword(AppUserDTO u, boolean isValid){
        user.setUsername("username");
        user.setEmail("email@email.com");
        setRoleDetails();
        assertUserDetails(u, isValid);
    }

    private void testEmail(AppUserDTO u, boolean isValid){
        user.setUsername("username");
        user.setPassword("password");
        setRoleDetails();
        assertUserDetails(u, isValid);
    }

    private void testRole(AppUserDTO u, boolean isValid){
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email@email.com");
        assertUserDetails(u, isValid);
    }

    private void assertUserDetails(AppUserDTO u, boolean isValid){
        Set<ConstraintViolation<AppUserDTO>> violations = validator.validate(u);
        assertEquals(violations.isEmpty(), isValid);
    }

    @Test
    public void test01UsernameIsTooLarge(){
        user.setUsername(tooLargeString);
        testUsername(user, false);
    }
    @Test
    public void test02UsernameIsTooSmall(){
        user.setUsername(tooSmallString);
        testUsername(user, false);
    }
    @Test
    public void test03UsernameIsNull(){
        testUsername(user, false);
    }
    @Test
    public void test04UsernameIsValid(){
        user.setUsername(validString);
        testUsername(user, true);
    }
    @Test
    public void test05PasswordIsTooLarge(){
        user.setPassword(tooLargeString);
        testPassword(user, false);
    }
    @Test
    public void test06PasswordIsTooSmall(){
        user.setPassword(tooSmallString);
        testPassword(user, false);
    }
    @Test
    public void test07PasswordIsNull(){
        testPassword(user, false);
    }
    @Test
    public void test08PasswordIsValid(){
        user.setPassword(validString);
        testPassword(user, true);
    }
    @Test
    public void test09EmailIsNull(){
        testEmail(user, false);
    }
    @Test
    public void test10EmailIsInvalid(){
        String invalidEmail = "invalidEmail";
        user.setEmail(invalidEmail);
        testEmail(user, false);
    }
    @Test
    public void test11EmailIsValid(){
        String validEmail = "email@email.com";
        user.setEmail(validEmail);
        testEmail(user, true);
    }
    @Test
    public void test12RoleIsNull(){
        testRole(user, false);
    }
    @Test
    public void test13RoleIsInitialised(){
        setRoleDetails();
        testRole(user, true);
    }
}
