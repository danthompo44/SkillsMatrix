package com.university.skillsmatrix.DTO;

import com.university.skillsmatrix.domain.RoleDTO;
import org.junit.Before;
import org.junit.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleDTOValidatorTest {
    private Validator validator;
    final RoleDTO roleDTO = new RoleDTO();

    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        roleDTO.setId(1);
    }

    private void testRoleType(RoleDTO r, boolean isValid){
        Set<ConstraintViolation<RoleDTO>> violations = validator.validate(r);
        assertEquals(violations.isEmpty(), isValid);
    }

    @Test
    public void testRoleTypeIsTooLarge(){
        roleDTO.setType("abcdefghijklmnop");//16 chars
        testRoleType(roleDTO, false);
    }

    @Test
    public void testRoleTypeIsTooSmall(){
        roleDTO.setType("");
        testRoleType(roleDTO, false);
    }

    @Test
    public void testRoleTypeIsValid(){
        roleDTO.setType("Manager");
        testRoleType(roleDTO, true);
    }
}
