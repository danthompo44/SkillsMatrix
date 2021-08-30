package com.university.skillsmatrix.convertors.manager;

import com.university.skillsmatrix.convertor.manager.DTOToManagerConvertor;
import com.university.skillsmatrix.domain.AppUserDTO;
import com.university.skillsmatrix.domain.ManagerDTO;
import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import com.university.skillsmatrix.domain.RoleDTO;
import com.university.skillsmatrix.entity.Manager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DTOToManagerConvertorTest {
    private DTOToManagerConvertor convertor;
    private ManagerDTO managerDTO;
    private Manager manager;

    @Before
    public void init(){
        convertor = new DTOToManagerConvertor();
        managerDTO = new ManagerDTO();
        manager = new Manager();
        AppUserDTO user = new AppUserDTO();
        RoleDTO role = new RoleDTO();
        PersonalDetailsDTO details = new PersonalDetailsDTO();

        details.setId(5);
        details.setFirstName("First Name");
        details.setSurname("Surname");
        details.setAddressFirstLine("Address First Line");
        details.setAddressSecondLine("Address Second Line");
        details.setCounty("County");
        details.setPostcode("Postcode");

        role.setId(3);
        role.setType("Manager");

        user.setId(2);
        user.setUsername("Username");
        user.setEmail("email@email.com");
        user.setRole(role);

        managerDTO.setId(65);
        managerDTO.setUser(user);
        managerDTO.setDetails(details);
    }

    @Test
    public void test01WhenGivenAManagerDTOConvertorReturnsManager(){
        manager = convertor.convert(managerDTO);
        assertEquals(managerDTO.getId(), manager.getId());

        //Assert Managers Personal Details
        assertEquals(managerDTO.getDetails().getId(), manager.getDetails().getId());
        assertEquals(managerDTO.getDetails().getFirstName(), manager.getDetails().getFirstName());
        assertEquals(managerDTO.getDetails().getSurname(), manager.getDetails().getSurname());
        assertEquals(managerDTO.getDetails().getAddressFirstLine(), manager.getDetails().getAddressFirstLine());
        assertEquals(managerDTO.getDetails().getAddressSecondLine(), manager.getDetails().getAddressSecondLine());
        assertEquals(managerDTO.getDetails().getCounty(), manager.getDetails().getCounty());
        assertEquals(managerDTO.getDetails().getPostcode(), manager.getDetails().getPostcode());

        //Assert Managers User Details
        assertEquals(managerDTO.getUser().getId(), manager.getUser().getId());
        assertEquals(managerDTO.getUser().getUsername(), manager.getUser().getUsername());
        assertEquals(managerDTO.getUser().getEmail(), manager.getUser().getEmail());
        assertEquals(managerDTO.getUser().getPassword(), manager.getUser().getPassword());
//        assertEquals(managerDTO.getUser().getRole().getId(), manager.getUser().getRole().getId());
//        assertEquals(managerDTO.getUser().getRole().getType(), manager.getUser().getRole().getType());
    }
}
