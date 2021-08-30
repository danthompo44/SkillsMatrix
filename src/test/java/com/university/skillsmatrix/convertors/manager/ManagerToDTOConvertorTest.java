package com.university.skillsmatrix.convertors.manager;

import com.university.skillsmatrix.convertor.manager.ManagerToDTOConvertor;
import com.university.skillsmatrix.domain.ManagerDTO;
import com.university.skillsmatrix.entity.AppUser;
import com.university.skillsmatrix.entity.Manager;
import com.university.skillsmatrix.entity.PersonalDetails;
import com.university.skillsmatrix.entity.Role;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ManagerToDTOConvertorTest {
    private ManagerToDTOConvertor managerConvertor;
    private Manager manager;
    private ManagerDTO managerDTO;

    @Before
    public void init(){
        managerConvertor = new ManagerToDTOConvertor();
        manager = new Manager();
        managerDTO = new ManagerDTO();
        AppUser user = new AppUser();
        Role role = new Role();
        PersonalDetails details = new PersonalDetails();

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
//        user.setRole(role);

        manager.setId(1);
        manager.setUser(user);
        manager.setDetails(details);
    }

    @Test
    public void test01WhenGivenAManagerConvertorReturnsManagerDTO(){
        managerDTO = managerConvertor.convert(manager);
        assertEquals(manager.getId(), managerDTO.getId());

        //Assert Managers Personal Details
        assertEquals(manager.getDetails().getId(), managerDTO.getDetails().getId());
        assertEquals(manager.getDetails().getFirstName(), managerDTO.getDetails().getFirstName());
        assertEquals(manager.getDetails().getSurname(), managerDTO.getDetails().getSurname());
        assertEquals(manager.getDetails().getAddressFirstLine(), managerDTO.getDetails().getAddressFirstLine());
        assertEquals(manager.getDetails().getAddressSecondLine(), managerDTO.getDetails().getAddressSecondLine());
        assertEquals(manager.getDetails().getCounty(), managerDTO.getDetails().getCounty());
        assertEquals(manager.getDetails().getPostcode(), managerDTO.getDetails().getPostcode());

        //Assert Manager's User Details
        assertEquals(manager.getUser().getId(), managerDTO.getUser().getId());
        assertEquals(manager.getUser().getUsername(), managerDTO.getUser().getUsername());
        assertEquals(manager.getUser().getEmail(), managerDTO.getUser().getEmail());
        assertEquals(manager.getUser().getPassword(), managerDTO.getUser().getPassword());
//        assertEquals(manager.getUser().getRole().getId(), managerDTO.getUser().getRole().getId());
//        assertEquals(manager.getUser().getRole().getType(), managerDTO.getUser().getRole().getType());
    }
}
