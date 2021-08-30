package com.university.skillsmatrix.convertors.role;

import com.university.skillsmatrix.convertor.role.DTOToRoleConvertor;
import com.university.skillsmatrix.domain.RoleDTO;
import com.university.skillsmatrix.entity.Role;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DTOToRoleConvertorTest {
    private DTOToRoleConvertor convertor;
    private RoleDTO roleDTO;
    private Role role;

    @Before
    public void init(){
        convertor = new DTOToRoleConvertor();
        roleDTO = new RoleDTO();
        role = new Role();

        roleDTO.setId(4);
        roleDTO.setType("User");
    }

    @Test
    public void test01WhenGivenARoleDTOConvertorReturnsRole(){
        role = convertor.convert(roleDTO);

        assertEquals(roleDTO.getId(), role.getId());
        assertEquals(roleDTO.getType(), role.getType());
    }
}
