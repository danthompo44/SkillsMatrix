package com.university.skillsmatrix.convertors.user;

import com.university.skillsmatrix.convertor.user.AppUserToDTOConvertor;
import com.university.skillsmatrix.convertor.user.DTOToAppUserConvertor;
import com.university.skillsmatrix.domain.AppUserDTO;
import com.university.skillsmatrix.domain.RoleDTO;
import com.university.skillsmatrix.entity.AppUser;
import com.university.skillsmatrix.entity.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DTOToUserConvertorTest {
    private DTOToAppUserConvertor convertor;
    private AppUserDTO dto;

    @Before
    public void init(){
        convertor = new DTOToAppUserConvertor();
        dto = new AppUserDTO();
        RoleDTO role = new RoleDTO();

        role.setId(45);
        role.setType("User");

        dto.setId(54);
        dto.setUsername("Username");
        dto.setEmail("email@email.com");
        dto.setPassword("password");
        dto.setRole(role);
    }

    @Test
    public void test01WhenGivenAppUserConvertorReturnsAppUserDTO(){
        AppUser user = convertor.convert(dto);

        assertEquals(dto.getId(), user.getId());
        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getPassword(), user.getPassword());
        //Assert Role
        assertEquals(dto.getRole().getId(), user.getRole().getId());
        assertEquals(dto.getRole().getType(), user.getRole().getType());
    }
}
