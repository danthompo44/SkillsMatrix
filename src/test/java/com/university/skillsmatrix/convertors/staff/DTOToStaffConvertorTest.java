package com.university.skillsmatrix.convertors.staff;

import com.university.skillsmatrix.convertor.staff.DTOToStaffConvertor;
import com.university.skillsmatrix.domain.*;
import com.university.skillsmatrix.entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class DTOToStaffConvertorTest {
    private DTOToStaffConvertor convertor;
    private StaffDTO staffDTO;
    private Staff staff;

    private final PersonalDetailsDTO userDetails1 = new PersonalDetailsDTO();
    private final PersonalDetailsDTO userDetails2 = new PersonalDetailsDTO();
    private final RoleDTO staffRole = new RoleDTO();
    private final RoleDTO managerRole = new RoleDTO();
    private final AppUserDTO user1 = new AppUserDTO();
    private final AppUserDTO user2 = new AppUserDTO();
    private final ManagerDTO manager = new ManagerDTO();
    private final SkillCategoryDTO cat1 = new SkillCategoryDTO();
    private final SkillCategoryDTO cat2 = new SkillCategoryDTO();
    private final SkillDTO skill1 = new SkillDTO();
    private final SkillDTO skill2 = new SkillDTO();

    private Staff convertToStaff(StaffDTO dto){
        Staff s = new Staff();
        s.setId(dto.getId());
        s.setDetails(convertDetails(dto.getDetails()));
        s.setUser(convertUser(dto.getUser()));
        s.setManager(convertManager(dto.getManager()));
        s.setSkills(setSKills(dto.getSkills()));

        return s;
    }

    private PersonalDetails convertDetails(PersonalDetailsDTO dto){
        PersonalDetails details = new PersonalDetails();
        details.setId(dto.getId());
        details.setFirstName(dto.getFirstName());
        details.setSurname(dto.getSurname());
        details.setAddressFirstLine(dto.getAddressFirstLine());
        details.setAddressSecondLine(dto.getAddressSecondLine());
        details.setCounty(dto.getCounty());
        details.setPostcode(dto.getPostcode());

        return details;
    }

    private AppUser convertUser(AppUserDTO dto){
        AppUser user = new AppUser();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    private Manager convertManager(ManagerDTO dto){
        Manager manager = new Manager();
        manager.setId(dto.getId());
        manager.setUser(convertUser(dto.getUser()));
        manager.setDetails(convertDetails(dto.getDetails()));

        return manager;
    }

    private List<Skill> setSKills(List<SkillDTO> skillsDTO){
        List<Skill> skills = new ArrayList<>();
        for(SkillDTO dto: skillsDTO){
            Skill skill = convertSKill(dto);
            skills.add(skill);
        }
        return skills;
    }

    private Skill convertSKill(SkillDTO dto){
        Skill skill = new Skill();
        skill.setId(dto.getId());
        skill.setName(dto.getName());
        skill.setCategory(convertCategory(dto.getCategory()));
        return skill;
    }

    private SkillCategory convertCategory(SkillCategoryDTO dto){
        SkillCategory category = new SkillCategory();
        category.setId(dto.getId());
        category.setDescription(dto.getDescription());

        return category;
    }

    @Before
    public void init(){
        convertor = new DTOToStaffConvertor();
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
        List<SkillDTO> staff1SkillsDTO = new ArrayList<>();
        staff1SkillsDTO.add(skill1);
        staff1SkillsDTO.add(skill2);

        //Set Staff
        staffDTO.setId(1L);
        staffDTO.setDetails(userDetails1);
        staffDTO.setUser(user2);
        staffDTO.setManager(manager);
        staffDTO.setSkills(staff1SkillsDTO);

        //Staff List for SKill 1
        List<StaffDTO> staffList1DTO = new ArrayList<>();
        staffList1DTO.add(staffDTO);

        //Staff List for SKill 2
        List<StaffDTO> staffList2DTO = new ArrayList<>();
        staffList2DTO.add(staffDTO);

        //Set Skills List of Staff
        skill1.setStaffList(staffList1DTO);
        skill2.setStaffList(staffList2DTO);

        //Convert To Staff DTOs
        staff = convertToStaff(staffDTO);
    }

    @Test
    public void test01_When_Given_A_Staff_Return_StaffDTO(){
        final Staff newStaff = convertor.convert(staffDTO);
        assertNotNull(newStaff);

        //Assert Id
        assertEquals(newStaff.getId(), staff.getId());

        //Assert Details
        assertEquals(newStaff.getDetails().getId(), staff.getDetails().getId());
        assertSame(newStaff.getDetails().getFirstName(), staff.getDetails().getFirstName());
        assertSame(newStaff.getDetails().getSurname(), staff.getDetails().getSurname());
        assertSame(newStaff.getDetails().getAddressFirstLine(), staff.getDetails().getAddressFirstLine());
        assertSame(newStaff.getDetails().getAddressSecondLine(), staff.getDetails().getAddressSecondLine());
        assertSame(newStaff.getDetails().getCounty(), staff.getDetails().getCounty());
        assertSame(newStaff.getDetails().getPostcode(), staff.getDetails().getPostcode());

        //Assert Manager
        assertEquals(newStaff.getManager().getId(), staff.getManager().getId());
        //Assert Manager User
        assertEquals(newStaff.getManager().getUser().getId(), staff.getManager().getUser().getId());
        assertSame(newStaff.getManager().getUser().getUsername(), staff.getManager().getUser().getUsername());
        assertSame(newStaff.getManager().getUser().getEmail(), staff.getManager().getUser().getEmail());
        assertSame(newStaff.getManager().getUser().getPassword(), staff.getManager().getUser().getPassword());
        //Assert Manager Role
//        assertEquals(newStaff.getManager().getUser().getRole().getId(), staff.getManager().getUser().getRole().getId());
//        assertSame(newStaff.getManager().getUser().getRole().getType(), staff.getManager().getUser().getRole().getType());
        //Assert Manager Details
        assertEquals(newStaff.getManager().getDetails().getId(), staff.getManager().getDetails().getId());
        assertSame(newStaff.getManager().getDetails().getFirstName(), staff.getManager().getDetails().getFirstName());
        assertSame(newStaff.getManager().getDetails().getSurname(), staff.getManager().getDetails().getSurname());
        assertSame(newStaff.getManager().getDetails().getAddressFirstLine(), staff.getManager().getDetails().getAddressFirstLine());
        assertSame(newStaff.getManager().getDetails().getAddressSecondLine(), staff.getManager().getDetails().getAddressSecondLine());
        assertSame(newStaff.getManager().getDetails().getCounty(), staff.getManager().getDetails().getCounty());
        assertSame(newStaff.getManager().getDetails().getPostcode(), staff.getManager().getDetails().getPostcode());

        //Assert User
        assertEquals(newStaff.getUser().getId(), staff.getUser().getId());
        assertSame(newStaff.getUser().getUsername(), staff.getUser().getUsername());
        assertSame(newStaff.getUser().getEmail(), staff.getUser().getEmail());
        assertSame(newStaff.getUser().getPassword(), staff.getUser().getPassword());
//        assertEquals(newStaff.getUser().getRole().getId(), staff.getUser().getRole().getId());
//        assertSame(newStaff.getUser().getRole().getType(), staff.getUser().getRole().getType());
    }

}
