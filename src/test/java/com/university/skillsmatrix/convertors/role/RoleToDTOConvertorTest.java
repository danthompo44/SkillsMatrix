package com.university.skillsmatrix.convertors.role;

import com.university.skillsmatrix.convertor.role.RoleToDTOConvertor;
import com.university.skillsmatrix.domain.RoleDTO;
import com.university.skillsmatrix.entity.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleToDTOConvertorTest {
    private RoleToDTOConvertor convertor;
    private Role role;
    private RoleDTO roleDTO;

    @Before
    public void init(){
        convertor = new RoleToDTOConvertor();
        role = new Role();
        roleDTO = new RoleDTO();

        role.setId(5);
        role.setType("Manager");
    }

    @Test
    public void test01WhenGivenARoleConvertorReturnsRoleDTO(){
        roleDTO = convertor.convert(role);

        assertEquals(role.getId(), roleDTO.getId());
        assertEquals(role.getType(), roleDTO.getType());
    }
}
