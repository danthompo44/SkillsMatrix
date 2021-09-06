package com.university.skillsmatrix.services;

import com.university.skillsmatrix.convertor.staffSkill.DTOToStaffSkillConvertor;
import com.university.skillsmatrix.convertor.staffSkill.StaffSkillDTOToStaffSkillIdDTOConvertor;
import com.university.skillsmatrix.convertor.staffSkill.StaffSkillToDTOConvertor;
import com.university.skillsmatrix.domain.*;
import com.university.skillsmatrix.entity.*;
import com.university.skillsmatrix.repository.StaffSkillRepository;
import com.university.skillsmatrix.service.StaffSkillService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffSkillServiceTest {
    @Mock private StaffSkillRepository repo;
    @Mock private StaffSkillToDTOConvertor staffSkillConvertor;
    @Mock private StaffSkillDTOToStaffSkillIdDTOConvertor staffSkillIdConvertor;
    @Mock private DTOToStaffSkillConvertor dtoToStaffSkillConvertor;

    private AutoCloseable closeable;

    @InjectMocks
    private StaffSkillService service;

    private final StaffSkill staffSkill1 = new StaffSkill();
    private final StaffSkill staffSkill2 = new StaffSkill();
    private final StaffSkill staffSkill3 = new StaffSkill();

    private final Staff staff1 = new Staff();
    private final PersonalDetails details1 = new PersonalDetails();
    private final AppUser user1 = new AppUser();

    private final Staff staff2 = new Staff();
    private final PersonalDetails details2 = new PersonalDetails();
    private final AppUser user2 = new AppUser();

    private final Manager manager1 = new Manager();
    private final PersonalDetails details3 = new PersonalDetails();
    private final AppUser user3 = new AppUser();

    private final Skill skill1 = new Skill();
    private final Skill skill2 = new Skill();
    private final Skill skill3 = new Skill();

    private final SkillCategory category1 = new SkillCategory();
    private final SkillCategory category2 = new SkillCategory();

    private final List<Skill> skillList1 = new ArrayList<>();
    private final List<Skill> skillList2 = new ArrayList<>();

    private StaffSkillDTO dto1 = new StaffSkillDTO();
    private StaffSkillDTO dto2 = new StaffSkillDTO();
    private StaffSkillDTO dto3 = new StaffSkillDTO();

    @BeforeEach
    void init(){
        closeable = openMocks(this);

        //Set Details
        details1.setId(92);
        details1.setFirstName("Dave");
        details1.setSurname("Burbidge");
        details1.setAddressFirstLine("Address line 1");
        details1.setAddressSecondLine("Address line 2");
        details1.setCounty("County 1");
        details1.setPostcode("Postcode 1");

        //Set Details 2
        details2.setId(93);
        details2.setFirstName("Matt");
        details2.setSurname("Morgan");
        details2.setAddressFirstLine("Address line 1");
        details2.setAddressSecondLine("Address line 2");
        details2.setCounty("County 2");
        details2.setPostcode("Postcode 2");

        //Set Details 3
        details3.setId(94);
        details3.setFirstName("Mike");
        details3.setSurname("Myers");
        details3.setAddressFirstLine("Address line 1");
        details3.setAddressSecondLine("Address line 2");
        details3.setCounty("County 3");
        details3.setPostcode("Postcode 3");

        //Set AppUser
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
        manager1.setId(97);
        manager1.setDetails(details3);
        manager1.setUser(user1);

        //Set Category
        category1.setId(339);
        category1.setDescription("Software");

        category2.setId(338);
        category2.setDescription("Networking");

        //Set Skills
        skill1.setId(58);
        skill1.setName("Java");
        skill1.setCategory(category1);

        skill2.setId(37);
        skill2.setName("Python");
        skill2.setCategory(category1);

        skill3.setId(38);
        skill3.setName("VLANs");
        skill3.setCategory(category2);

        //Set Skill Lists
        skillList1.add(skill1);
        skillList1.add(skill2);

        skillList2.add(skill3);

        //Set Staff
        staff1.setId(34);
        staff1.setDetails(details1);
        staff1.setManager(manager1);
        staff1.setUser(user2);
        staff1.setSkills(skillList1);

        staff2.setId(97);
        staff2.setDetails(details2);
        staff2.setManager(manager1);
        staff2.setUser(user3);
        staff2.setSkills(skillList2);

        //Set Staff SKills
        staffSkill1.setId(23);
        staffSkill1.setSkillStrength(7);
        staffSkill1.setSkill(skill1);
        staffSkill1.setStaff(staff1);

        staffSkill2.setId(47);
        staffSkill2.setSkillStrength(6);
        staffSkill2.setSkill(skill2);
        staffSkill2.setStaff(staff1);

        staffSkill3.setId(77);
        staffSkill3.setSkillStrength(9);
        staffSkill3.setSkill(skill3);
        staffSkill3.setStaff(staff2);

        dto1 = convert(staffSkill1);
        dto2 = convert(staffSkill2);
        dto3 = convert(staffSkill3);
    }

    @AfterEach
    void close() throws Exception{
        closeable.close();
    }

    private StaffSkillDTO convert(StaffSkill s){
        StaffSkillDTO dto = new StaffSkillDTO();
        dto.setId(s.getId());
        dto.setExpiryDate(s.getExpiryDate());
        dto.setSkillStrength(s.getSkillStrength());
        dto.setSkill(convertSkill(s.getSkill()));
        dto.setStaff(convertStaff(s.getStaff()));

        return dto;
    }

    private StaffDTO convertStaff(Staff staff){
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

    @Test
    public void test01WhenGivenARequestsForStaffSkillsByStaffIdReturnExpected(){
        List<StaffSkill> requestedListOfStaffSkills = new ArrayList<>();
        requestedListOfStaffSkills.add(staffSkill1);
        requestedListOfStaffSkills.add(staffSkill2);
        when(repo.findAllByStaffId(34L)).thenReturn(requestedListOfStaffSkills);

        //StaffConvertor Mock Behaviour
        when(staffSkillConvertor.convert(staffSkill1)).thenReturn(dto1);
        when(staffSkillConvertor.convert(staffSkill2)).thenReturn(dto2);

        //Define expected response objects
        List<StaffSkillDTO> expectedListOfStaffSkills = new ArrayList<>();
        expectedListOfStaffSkills.add(dto1);
        expectedListOfStaffSkills.add(dto2);

        List<StaffSkillDTO> newStaffSkills = service.getStaffSkillsByStaffId(34L);

        //Assert sizes are the same
        Assertions.assertEquals(newStaffSkills.size(), requestedListOfStaffSkills.size());

        //Check elements are the same
        for(int i = 0; i< newStaffSkills.size(); i++){
            Assertions.assertEquals(newStaffSkills.get(i), expectedListOfStaffSkills.get(i));
        }
    }

    @Test
    public void test02WhenGivenARequestToRetrieveStaffSkillByIdStaffSkillIsReturned(){
        when(repo.findById(23L)).thenReturn(Optional.of(staffSkill1));

        //Convertor Mock Behaviour
        when(staffSkillConvertor.convert(staffSkill1)).thenReturn(dto1);

        StaffSkillDTO expectedStaffSkill = dto1;

        StaffSkillDTO newStaffSkill = service.findById(23L);

        //Assert are the same
        Assertions.assertEquals(expectedStaffSkill, newStaffSkill);
    }

    @Test
    public void test03WhenGivenARequestToSaveASkillItIsSaved(){
        List<StaffSkill> requestedListOfStaffSkills = new ArrayList<>();
        requestedListOfStaffSkills.add(staffSkill1);
        when(repo.findAll()).thenReturn(requestedListOfStaffSkills);

        //StaffConvertor Mock Behaviour
        when(staffSkillConvertor.convert(staffSkill1)).thenReturn(dto1);
        when(staffSkillConvertor.convert(staffSkill2)).thenReturn(dto2);

        //Define expected response objects
        List<StaffSkillDTO> expectedListOfStaffSkills = new ArrayList<>();
        expectedListOfStaffSkills.add(dto1);

        List<StaffSkillDTO> newSkills = service.getAllStaffSkills();

        //Assert sizes are the same
        Assertions.assertEquals(newSkills.size(), requestedListOfStaffSkills.size());

        //Check elements are the same
        for(int i = 0; i< newSkills.size(); i++){
            Assertions.assertEquals(newSkills.get(i), expectedListOfStaffSkills.get(i));
        }

        when(repo.save(staffSkill2)).thenReturn(staffSkill2);

        StaffSkillDTO savedStaffSkill = service.save(dto2);

        requestedListOfStaffSkills.add(staffSkill2);
        when(repo.findAll()).thenReturn(requestedListOfStaffSkills);

        expectedListOfStaffSkills.add(dto2);
        newSkills = service.getAllStaffSkills();

        //Assert sizes are the same
        Assertions.assertEquals(newSkills.size(), requestedListOfStaffSkills.size());

        //Check elements are the same
        for(int i = 0; i< newSkills.size(); i++){
            Assertions.assertEquals(newSkills.get(i), expectedListOfStaffSkills.get(i));
        }
    }

    @Test
    public void test04WhenGivenARequestToDeleteAStaffSkillItIsDeleted(){
        List<StaffSkill> requestedListOfStaffSkills = new ArrayList<>();
        requestedListOfStaffSkills.add(staffSkill1);
        requestedListOfStaffSkills.add(staffSkill2);
        when(repo.findAll()).thenReturn(requestedListOfStaffSkills);

        //Cat Convertor Mock Behaviour
        when(staffSkillConvertor.convert(staffSkill1)).thenReturn(dto1);
        when(staffSkillConvertor.convert(staffSkill2)).thenReturn(dto2);

        //Define List of expected response objects
        List<StaffSkillDTO> expectedListOfStaffSkills = new ArrayList<>();
        expectedListOfStaffSkills.add(dto1);
        expectedListOfStaffSkills.add(dto2);

        List<StaffSkillDTO> newStaffSkills = service.getAllStaffSkills();

        //Assert sizes are the same
        assertEquals(newStaffSkills.size(), requestedListOfStaffSkills.size());

        //Check elements are the same
        for(int i = 0; i < newStaffSkills.size(); i++){
            assertEquals(newStaffSkills.get(i), expectedListOfStaffSkills.get(i));
        }

        //Remove objects from mock behaviours
        requestedListOfStaffSkills.remove(staffSkill2);
        when(repo.findAll()).thenReturn(requestedListOfStaffSkills);
        expectedListOfStaffSkills.remove(dto2);

        service.deleteStaffSKillById(staffSkill2.getId());

        newStaffSkills = service.getAllStaffSkills();
        //Assert sizes are the same
        assertEquals(newStaffSkills.size(), requestedListOfStaffSkills.size());

        //Check elements are the same
        for(int i = 0; i < newStaffSkills.size(); i++){
            assertEquals(newStaffSkills.get(i), expectedListOfStaffSkills.get(i));
        }

    }
}
