package com.university.skillsmatrix.convertors.user;

import com.university.skillsmatrix.convertor.user.AppUserToDTOConvertor;
import com.university.skillsmatrix.domain.AppUserDTO;
import com.university.skillsmatrix.entity.AppUser;
import com.university.skillsmatrix.entity.Role;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserToDTOConvertorTest {
    private AppUserToDTOConvertor convertor;
    private AppUser user;

    @Before
    public void init(){
        convertor = new AppUserToDTOConvertor();
        user = new AppUser();
        Role role = new Role();

        role.setId(45);
        role.setType("User");

        user.setId(54);
        user.setUsername("Username");
        user.setEmail("email@email.com");
        user.setPassword("password");
        user.setRole(role);
    }

    @Test
    public void test01WhenGivenAppUserConvertorReturnsAppUserDTO(){
        AppUserDTO dto = convertor.convert(user);

        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getUsername(), dto.getUsername());
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getPassword(), dto.getPassword());
        //Assert Role
        assertEquals(user.getRole().getId(), dto.getRole().getId());
        assertEquals(user.getRole().getType(), dto.getRole().getType());
    }
}
