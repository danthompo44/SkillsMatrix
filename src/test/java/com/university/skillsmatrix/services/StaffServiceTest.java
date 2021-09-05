package com.university.skillsmatrix.services;

import com.university.skillsmatrix.convertor.staff.StaffToDTOConvertor;
import com.university.skillsmatrix.domain.*;
import com.university.skillsmatrix.entity.*;
import com.university.skillsmatrix.repository.StaffRepository;
import com.university.skillsmatrix.service.StaffService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffServiceTest {
    @Mock private StaffRepository staffRepository;
    @Mock private StaffToDTOConvertor staffConvertor;

    private AutoCloseable closeable;

    @InjectMocks
    private StaffService staffService;

    //Dummy Objects
    private final PersonalDetails userDetails1 = new PersonalDetails();
    private final PersonalDetails userDetails2 = new PersonalDetails();
    private final PersonalDetails userDetails3 = new PersonalDetails();
    private final Role staffRole = new Role();
    private final Role managerRole = new Role();
    private final AppUser user1 = new AppUser();
    private final AppUser user2 = new AppUser();
    private final AppUser user3 = new AppUser();
    private final Manager manager = new Manager();
    private final SkillCategory cat1 = new SkillCategory();
    private final SkillCategory cat2 = new SkillCategory();
    private final Skill skill1 = new Skill();
    private final Skill skill2 = new Skill();
    private final Skill skill3 = new Skill();
    private final Staff staff1 = new Staff();
    private final Staff staff2 = new Staff();
    private StaffDTO dto1 = null;
    private StaffDTO dto2 = null;

    private StaffDTO convertToDTO(Staff staff){
        StaffDTO dto = new StaffDTO();
        dto.setId(staff.getId());
        dto.setDetails(convertDetails(staff.getDetails()));
        dto.setUser(convertUser(staff.getUser()));
        dto.setManager(convertManager(staff.getManager()));
        dto.setSkills(setSkills(staff.getSkills()));

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

        return dto;
    }

    private ManagerDTO convertManager(Manager m){
        ManagerDTO dto = new ManagerDTO();
        dto.setId(m.getId());
        dto.setUser(convertUser(m.getUser()));
        dto.setDetails(convertDetails(m.getDetails()));

        return dto;
    }

    private List<SkillDTO> setSkills(List<Skill> skills){
        List<SkillDTO> skillDTOS = new ArrayList<>();
        for(Skill s: skills){
            SkillDTO dto = convertSkill(s);
            skillDTOS.add(dto);
        }
        return skillDTOS;
    }

    private SkillDTO convertSkill(Skill s){
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

    @BeforeEach
    void init(){
        closeable = openMocks(this);

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

        //Set Details 3
        userDetails3.setId(94);
        userDetails3.setFirstName("Mike");
        userDetails3.setSurname("Myers");
        userDetails3.setAddressFirstLine("Address line 1");
        userDetails3.setAddressSecondLine("Address line 2");
        userDetails3.setCounty("County 3");
        userDetails3.setPostcode("Postcode 3");

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

        //Set AppUser 2
        user2.setId(104);
        user2.setUsername("Username 2");
        user2.setEmail("email2@email.com");
        user2.setPassword("password");

        //Set AppUser 3
        user3.setId(105);
        user3.setUsername("Username 3");
        user3.setEmail("email3@email.com");
        user3.setPassword("password");

        //Set Manager
        manager.setId(72);
        manager.setDetails(userDetails3);
        manager.setUser(user1);

        //Set Cat1
        cat1.setId(39);
        cat1.setDescription("Software");

        //Set Cat2
        cat2.setId(40);
        cat2.setDescription("Networks");

        //Set Skill 1
        skill1.setId(432);
        skill1.setName("Java Programming");
        skill1.setCategory(cat1);

        //Set Skill 2
        skill2.setId(433);
        skill2.setName("Git CLI");
        skill2.setCategory(cat1);

        //Set Skill 3
        skill3.setId(434);
        skill3.setName("VLANs");
        skill3.setCategory(cat2);

        //Staff 1's skills
        List<Skill> staff1Skills = new ArrayList<>();
        staff1Skills.add(skill2);
        staff1Skills.add(skill3);

        //Staff 2's Skills
        List<Skill> staff2Skills = new ArrayList<>();
        staff2Skills.add(skill1);
        staff2Skills.add(skill2);
        staff2Skills.add(skill3);

        //Set Staff
        staff1.setId(1L);
        staff1.setDetails(userDetails1);
        staff1.setUser(user2);
        staff1.setManager(manager);
        staff1.setSkills(staff1Skills);

        staff2.setId(2L);
        staff2.setDetails(userDetails2);
        staff2.setUser(user3);
        staff2.setManager(manager);
        staff2.setSkills(staff2Skills);

        //Staff List for SKill 1
        List<Staff> staffList1 = new ArrayList<>();
        staffList1.add(staff2);

        //Staff List for SKill 2
        List<Staff> staffList2 = new ArrayList<>();
        staffList2.add(staff1);
        staffList2.add(staff2);

        //Staff List for SKill 3
        List<Staff> staffList3 = new ArrayList<>();
        staffList3.add(staff1);
        staffList3.add(staff2);

        //Set Skills List of Staff
        skill1.setStaffList(staffList1);
        skill2.setStaffList(staffList2);
        skill3.setStaffList(staffList3);

        //Convert To Staff DTOs
        dto1 = convertToDTO(staff1);
        dto2 = convertToDTO(staff2);
    }

    @AfterEach
    void close() throws Exception{
        closeable.close();
    }

    @Test
    public void test01_When_Given_A_Request_For_All_Staff_Return_All(){
        List<Staff> requestedListOfStaff = new ArrayList<>();
        requestedListOfStaff.add(staff1);
        requestedListOfStaff.add(staff2);
        when(staffRepository.findAll()).thenReturn(requestedListOfStaff);

        //StaffConvertor Mock Behaviour
        when(staffConvertor.convert(staff1)).thenReturn(dto1);
        when(staffConvertor.convert(staff2)).thenReturn(dto2);

        //Define expected response objects
        List<StaffDTO> expectedListOfStaff = new ArrayList<>();
        expectedListOfStaff.add(dto1);
        expectedListOfStaff.add(dto2);

        List<StaffDTO> newStaff = staffService.getAllStaff();

        //Assert sizes are the same
        assertEquals(newStaff.size(), requestedListOfStaff.size());

        //Check elements are the same
        for(int i = 0; i< newStaff.size(); i++){
            assertEquals(newStaff.get(i), expectedListOfStaff.get(i));
        }
    }
}
