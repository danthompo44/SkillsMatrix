package com.university.skillsmatrix.services;

import com.university.skillsmatrix.convertor.skill.DTOToSkillConvertor;
import com.university.skillsmatrix.convertor.skill.SkillToDTOConvertor;
import com.university.skillsmatrix.domain.ManagerDTO;
import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.domain.StaffDTO;
import com.university.skillsmatrix.entity.*;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.repository.SkillRepository;
import com.university.skillsmatrix.service.SkillService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SkillServiceTest {
    @Mock private SkillRepository repo;
    @Mock private SkillToDTOConvertor skillToDTOConvertor;
    @Mock private DTOToSkillConvertor dtoToSkillConvertor;

    private AutoCloseable closeable;

    @InjectMocks
    private SkillService service;

    private final Skill s1 = new Skill();
    private final Skill s2 = new Skill();
    private final SkillCategory cat1 = new SkillCategory();
    private final Staff staff1 = new Staff();
    private final Staff staff2 = new Staff();
    private final PersonalDetails details = new PersonalDetails();
    private final PersonalDetails details2 = new PersonalDetails();
    private final AppUser user = new AppUser();
    private final AppUser user2 = new AppUser();
    private final Manager m1 = new Manager();
    private final List<Staff> staffList = new ArrayList<>();
    private final List<Staff> staffList2 = new ArrayList<>();
    private SkillDTO dto1 = new SkillDTO();
    private SkillDTO dto2 = new SkillDTO();

    @BeforeEach
    void init() {
        closeable = openMocks(this);

        cat1.setId(23L);
        cat1.setDescription("Software");

        //Set Details
        details.setId(92);
        details.setFirstName("Dave");
        details.setSurname("Burbidge");
        details.setAddressFirstLine("Address line 1");
        details.setAddressSecondLine("Address line 2");
        details.setCounty("County 1");
        details.setPostcode("Postcode 1");

        //Set Details 2
        details2.setId(93);
        details2.setFirstName("Matt");
        details2.setSurname("Morgan");
        details2.setAddressFirstLine("Address line 1");
        details2.setAddressSecondLine("Address line 2");
        details2.setCounty("County 2");
        details2.setPostcode("Postcode 2");

        //Set AppUser
        user.setId(103);
        user.setUsername("Username 1");
        user.setEmail("email1@email.com");
        user.setPassword("password");

        //Set AppUser 2
        user2.setId(104);
        user2.setUsername("Username 2");
        user2.setEmail("email2@email.com");
        user2.setPassword("password");

        //Set Manager
        m1.setId(34L);
        m1.setDetails(details);
        m1.setUser(user);

        staff1.setId(12L);
        staff1.setDetails(details);
        staff1.setUser(user);
        staff1.setManager(m1);

        staff2.setId(34L);
        staff2.setDetails(details2);
        staff2.setUser(user2);
        staff2.setManager(m1);

        staffList.add(staff1);
        staffList2.add(staff2);

        s1.setId(34L);
        s1.setName("Python");
        s1.setCategory(cat1);
        s1.setStaffList(staffList);

        s2.setId(67L);
        s2.setName("Java");
        s2.setCategory(cat1);
        s2.setStaffList(staffList2);

        dto1 = convert(s1);
        dto2 = convert(s2);
    }

    @AfterEach
    void close() throws Exception{
        closeable.close();
    }

    private SkillDTO convert(Skill s){
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
    public void test01_When_Given_A_Request_For_All_Skills_Return_All(){
        List<Skill> requestedListOfSkills = new ArrayList<>();
        requestedListOfSkills.add(s1);
        requestedListOfSkills.add(s2);
        when(repo.findAll()).thenReturn(requestedListOfSkills);

        //StaffConvertor Mock Behaviour
        when(skillToDTOConvertor.convert(s1)).thenReturn(dto1);
        when(skillToDTOConvertor.convert(s2)).thenReturn(dto2);

        //Define expected response objects
        List<SkillDTO> expectedListOfSkills = new ArrayList<>();
        expectedListOfSkills.add(dto1);
        expectedListOfSkills.add(dto2);

        List<SkillDTO> newSkills = service.getAllSkills();

        //Assert sizes are the same
        Assertions.assertEquals(newSkills.size(), requestedListOfSkills.size());

        //Check elements are the same
        for(int i = 0; i< newSkills.size(); i++){
            Assertions.assertEquals(newSkills.get(i), expectedListOfSkills.get(i));
        }
    }

    @Test
    public void test02WhenGivenARequestToRetrieveSkillByIdSkillIsReturned(){
        when(repo.findById(34L)).thenReturn(Optional.of(s1));

        //Convertor Mock Behaviour
        when(skillToDTOConvertor.convert(s1)).thenReturn(dto1);

        SkillDTO expectedSkill = dto1;

        SkillDTO newSkill = service.getSkillById(34L);

        //Assert are the same
        Assertions.assertEquals(newSkill, expectedSkill);
    }

    @Test
    public void test03WhenGivenARequestToSaveASkillItIsSaved(){
        List<Skill> requestedListOfSkills = new ArrayList<>();
        requestedListOfSkills.add(s1);
        when(repo.findAll()).thenReturn(requestedListOfSkills);

        //StaffConvertor Mock Behaviour
        when(skillToDTOConvertor.convert(s1)).thenReturn(dto1);
        when(skillToDTOConvertor.convert(s2)).thenReturn(dto2);

        //Define expected response objects
        List<SkillDTO> expectedListOfSkills = new ArrayList<>();
        expectedListOfSkills.add(dto1);

        List<SkillDTO> newSkills = service.getAllSkills();

        //Assert sizes are the same
        Assertions.assertEquals(newSkills.size(), requestedListOfSkills.size());

        //Check elements are the same
        for(int i = 0; i< newSkills.size(); i++){
            Assertions.assertEquals(newSkills.get(i), expectedListOfSkills.get(i));
        }

        when(repo.save(s2)).thenReturn(s2);

        SkillDTO savedSkill = service.save(dto2);

        requestedListOfSkills.add(s2);
        when(repo.findAll()).thenReturn(requestedListOfSkills);

        expectedListOfSkills.add(dto2);
        newSkills = service.getAllSkills();

        //Assert sizes are the same
        Assertions.assertEquals(newSkills.size(), requestedListOfSkills.size());

        //Check elements are the same
        for(int i = 0; i< newSkills.size(); i++){
            Assertions.assertEquals(newSkills.get(i), expectedListOfSkills.get(i));
        }

    }
}
