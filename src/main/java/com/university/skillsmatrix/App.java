package com.university.skillsmatrix;

import com.university.skillsmatrix.convertor.staff.StaffToDTOConvertor;
import com.university.skillsmatrix.domain.StaffDTO;
import com.university.skillsmatrix.entity.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        SkillCategory cat = new SkillCategory();
        cat.setId(32);
        cat.setDescription("A Category");

        Role userRole = new Role();
        userRole.setId(10);
        userRole.setType("User");

        Role managerRole = new Role();
        managerRole.setId(9);
        managerRole.setType("Manager");

        AppUser user = new AppUser();
        user.setId(12);
        user.setUsername("Dave");
        user.setPassword("password");
        user.setEmail("email@email.com");
        user.setRole(userRole);

        AppUser manUser = new AppUser();
        manUser.setId(16);
        manUser.setUsername("Manager");
        manUser.setEmail("email2@email.com");
        manUser.setRole(managerRole);
        manUser.setPassword("password");

        PersonalDetails userDetails1 = new PersonalDetails();
        userDetails1.setId(92);
        userDetails1.setFirstName("Dave");
        userDetails1.setSurname("Burbidge");
        userDetails1.setAddressFirstLine("Address 1 line");
        userDetails1.setAddressSecondLine("Address 2 line");
        userDetails1.setCounty("County 1");
        userDetails1.setPostcode("Postcode 1");

        PersonalDetails userDetails2 = new PersonalDetails();
        userDetails2.setId(93);
        userDetails2.setFirstName("Matt");
        userDetails2.setSurname("Morgan");
        userDetails2.setAddressFirstLine("Address 1 line");
        userDetails2.setAddressSecondLine("Address 2 line");
        userDetails2.setCounty("County 2");
        userDetails2.setPostcode("Postcode 2");

        Manager manager = new Manager();
        manager.setId(19);
        manager.setUser(manUser);
        manager.setDetails(userDetails1);


        Staff s1 = new Staff();
        s1.setId(21);
        s1.setUser(user);
        s1.setDetails(userDetails2);
        s1.setManager(manager);


        Skill s = new Skill();
        s.setId(42);
        s.setName("A skill");
        s.setCategory(cat);
        List<Staff> staffList = new ArrayList<>();
        staffList.add(s1);
        s.setStaffList(staffList);

        System.out.println("Skill\n");
        System.out.println(s);

        final StaffToDTOConvertor convertor = new StaffToDTOConvertor();
        StaffDTO dto = convertor.convert(s1);
        System.out.println("\n\nDTO\n\n");
        System.out.println(dto);
    }
}
