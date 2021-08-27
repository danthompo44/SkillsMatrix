package com.university.skillsmatrix.convertors;

import com.university.skillsmatrix.convertor.staff.StaffToDTOConvertor;
import com.university.skillsmatrix.domain.*;
import com.university.skillsmatrix.entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StaffToDTOConvertorTest {
    private StaffToDTOConvertor convertor;
    private Staff staff;
    private StaffDTO staffDTO;

    private final PersonalDetails userDetails1 = new PersonalDetails();
    private final PersonalDetails userDetails2 = new PersonalDetails();
    private final Role staffRole = new Role();
    private final Role managerRole = new Role();
    private final AppUser user1 = new AppUser();
    private final AppUser user2 = new AppUser();
    private final Manager manager = new Manager();
    private final SkillCategory cat1 = new SkillCategory();
    private final SkillCategory cat2 = new SkillCategory();
    private final Skill skill1 = new Skill();
    private final Skill skill2 = new Skill();

    private StaffDTO convertToDTO(Staff staff){
        StaffDTO dto = new StaffDTO();
        dto.setId(staff.getId());
        dto.setDetails(convertDetails(staff.getDetails()));
        dto.setUser(convertUser(staff.getUser()));
        dto.setManager(convertManager(staff.getManager()));
        dto.setSkills(setSKills(staff.getSkills()));

        return dto;
    }

    private PersonalDetailsDTO convertDetails(PersonalDetails d){
        PersonalDetailsDTO dto = new PersonalDetailsDTO();
        dto.setId(d.getId());
        dto.setFirstName(d.getFirstName());
        dto.setSurname(d.getSurname());
        dto.setAddressFirstLine(d.getAddressFirstLine());
        dto.setAddressSecondLine(d.getAddressSecondLine());
        dto.setCounty(d.getCounty());
        dto.setPostcode(d.getPostcode());

        return dto;
    }

    private AppUserDTO convertUser(AppUser u){
        AppUserDTO dto = new AppUserDTO();
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setEmail(u.getEmail());
        dto.setPassword(u.getPassword());
        dto.setRole(convertRole(u.getRole()));

        return dto;
    }

    private RoleDTO convertRole(Role r){
        RoleDTO dto = new RoleDTO();
        dto.setId(r.getId());
        dto.setType(r.getType());

        return dto;
    }

    private ManagerDTO convertManager(Manager m){
        ManagerDTO dto = new ManagerDTO();
        dto.setId(m.getId());
        dto.setUser(convertUser(m.getUser()));
        dto.setDetails(convertDetails(m.getDetails()));

        return dto;
    }

    private List<SkillDTO> setSKills(List<Skill> skills){
        List<SkillDTO> skillDTOS = new ArrayList<>();
        for(Skill s: skills){
            SkillDTO dto = convertSKill(s);
            skillDTOS.add(dto);
        }
        return skillDTOS;
    }

    private SkillDTO convertSKill(Skill s){
        SkillDTO dto = new SkillDTO();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setCategory(convertCategory(s.getCategory()));
        return dto;
    }

    private SkillCategoryDTO convertCategory(SkillCategory c){
        SkillCategoryDTO dto = new SkillCategoryDTO();
        dto.setId(c.getId());
        dto.setDescription(c.getDescription());

        return dto;
    }

    @Before
    public void init(){
        convertor = new StaffToDTOConvertor();
        staff = new Staff();
        staffDTO = new StaffDTO();

        //Set Details 1
        userDetails1.setId(92);
        userDetails1.setFirstName("Dave");
        userDetails1.setSurname("Burbidge");
        userDetails1.setAddressFirstLine("Address line 1");
        userDetails1.setAddressSecondLine("Address line 2");
        userDetails1.setCounty("County 1");
        userDetails1.setPostcode("Postcode 1");

        //Set Details 2
        userDetails2.setId(93);
        userDetails2.setFirstName("Matt");
        userDetails2.setSurname("Morgan");
        userDetails2.setAddressFirstLine("Address line 1");
        userDetails2.setAddressSecondLine("Address line 2");
        userDetails2.setCounty("County 2");
        userDetails2.setPostcode("Postcode 2");

        //Set Role 1
        staffRole.setId(35);
        staffRole.setType("user");

        //Set Role 2
        managerRole.setId(36);
        managerRole.setType("manager");

        //Set AppUser 1
        user1.setId(103);
        user1.setUsername("Username 1");
        user1.setEmail("email1@email.com");
        user1.setPassword("password");
        user1.setRole(managerRole);

        //Set AppUser 2
        user2.setId(104);
        user2.setUsername("Username 2");
        user2.setEmail("email2@email.com");
        user2.setPassword("password");
        user2.setRole(staffRole);

        //Set Manager
        manager.setId(72);
        manager.setDetails(userDetails1);
        manager.setUser(user1);

        //Set Cat1
        cat1.setId(39);
        cat1.setDescription("Software");

        //Set Cat2
        cat2.setId(40);
        cat2.setDescription("Networks");

        //Set Skill 1
        skill1.setId(433);
        skill1.setName("Git CLI");
        skill1.setCategory(cat1);

        //Set Skill 2
        skill2.setId(434);
        skill2.setName("VLANs");
        skill2.setCategory(cat2);

        //Staff 1's skills
        List<Skill> staff1Skills = new ArrayList<>();
        staff1Skills.add(skill1);
        staff1Skills.add(skill2);

        //Set Staff
        staff.setId(1L);
        staff.setDetails(userDetails1);
        staff.setUser(user2);
        staff.setManager(manager);
        staff.setSkills(staff1Skills);

        //Staff List for SKill 1
        List<Staff> staffList1 = new ArrayList<>();
        staffList1.add(staff);

        //Staff List for SKill 2
        List<Staff> staffList2 = new ArrayList<>();
        staffList2.add(staff);

        //Set Skills List of Staff
        skill1.setStaffList(staffList1);
        skill2.setStaffList(staffList2);

        //Convert To Staff DTOs
        staffDTO = convertToDTO(staff);
    }

    @Test
    public void test01_When_Given_A_Staff_Return_StaffDTO(){
        final StaffDTO newDTO = convertor.convert(staff);
        assertNotNull(newDTO);

        //Assert Id
        assertEquals(newDTO.getId(), staffDTO.getId());

        //Assert Details
        assertEquals(newDTO.getDetails().getId(), staffDTO.getDetails().getId());
        assertSame(newDTO.getDetails().getFirstName(), staffDTO.getDetails().getFirstName());
        assertSame(newDTO.getDetails().getSurname(), staffDTO.getDetails().getSurname());
        assertSame(newDTO.getDetails().getAddressFirstLine(), staffDTO.getDetails().getAddressFirstLine());
        assertSame(newDTO.getDetails().getAddressSecondLine(), staffDTO.getDetails().getAddressSecondLine());
        assertSame(newDTO.getDetails().getCounty(), staffDTO.getDetails().getCounty());
        assertSame(newDTO.getDetails().getPostcode(), staffDTO.getDetails().getPostcode());

        //Assert Manager
        assertEquals(newDTO.getManager().getId(), staffDTO.getManager().getId());
        //Assert Manager User
        assertEquals(newDTO.getManager().getUser().getId(), staffDTO.getManager().getUser().getId());
        assertSame(newDTO.getManager().getUser().getUsername(), staffDTO.getManager().getUser().getUsername());
        assertSame(newDTO.getManager().getUser().getEmail(), staffDTO.getManager().getUser().getEmail());
        assertSame(newDTO.getManager().getUser().getPassword(), staffDTO.getManager().getUser().getPassword());
        //Assert Manager Role
        assertEquals(newDTO.getManager().getUser().getRole().getId(), staffDTO.getManager().getUser().getRole().getId());
        assertSame(newDTO.getManager().getUser().getRole().getType(), staffDTO.getManager().getUser().getRole().getType());
        //Assert Manager Details
        assertEquals(newDTO.getManager().getDetails().getId(), staffDTO.getManager().getDetails().getId());
        assertSame(newDTO.getManager().getDetails().getFirstName(), staffDTO.getManager().getDetails().getFirstName());
        assertSame(newDTO.getManager().getDetails().getSurname(), staffDTO.getManager().getDetails().getSurname());
        assertSame(newDTO.getManager().getDetails().getAddressFirstLine(), staffDTO.getManager().getDetails().getAddressFirstLine());
        assertSame(newDTO.getManager().getDetails().getAddressSecondLine(), staffDTO.getManager().getDetails().getAddressSecondLine());
        assertSame(newDTO.getManager().getDetails().getCounty(), staffDTO.getManager().getDetails().getCounty());
        assertSame(newDTO.getManager().getDetails().getPostcode(), staffDTO.getManager().getDetails().getPostcode());

        //Assert Skills
        assertEquals(newDTO.getSkills().size(), staffDTO.getSkills().size());
        for(int i =0; i < newDTO.getSkills().size(); i++){
            assertEquals(newDTO.getSkills().get(i), staffDTO.getSkills().get(i));
        }

        //Assert User
        assertEquals(newDTO.getUser().getId(), staffDTO.getUser().getId());
        assertSame(newDTO.getUser().getUsername(), staffDTO.getUser().getUsername());
        assertSame(newDTO.getUser().getEmail(), staffDTO.getUser().getEmail());
        assertSame(newDTO.getUser().getPassword(), staffDTO.getUser().getPassword());
        assertEquals(newDTO.getUser().getRole().getId(), staffDTO.getUser().getRole().getId());
        assertSame(newDTO.getUser().getRole().getType(), staffDTO.getUser().getRole().getType());
    }
}
