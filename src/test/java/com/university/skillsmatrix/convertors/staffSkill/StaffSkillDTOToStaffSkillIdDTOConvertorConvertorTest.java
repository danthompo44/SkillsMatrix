package com.university.skillsmatrix.convertors.staffSkill;

import com.university.skillsmatrix.convertor.staffSkill.StaffSkillDTOToStaffSkillIdDTOConvertor;
import com.university.skillsmatrix.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffSkillDTOToStaffSkillIdDTOConvertorConvertorTest {
    private StaffSkillDTOToStaffSkillIdDTOConvertor convertor;
    private StaffSkillDTO staffSkillDTO;
    private final List<StaffDTO> staffDTOS = new ArrayList<>();
    private final List<SkillDTO> skillDTOS = new ArrayList<>();

    @Before
    public void init(){
        convertor = new StaffSkillDTOToStaffSkillIdDTOConvertor();
        staffSkillDTO = new StaffSkillDTO();
        StaffDTO staffDTO = new StaffDTO();
        SkillDTO skillDTO = new SkillDTO();
        ManagerDTO managerDTO = new ManagerDTO();
        PersonalDetailsDTO detailsDTO = new PersonalDetailsDTO();
        PersonalDetailsDTO managerDetailsDTO = new PersonalDetailsDTO();
        AppUserDTO managerUserDTO = new AppUserDTO();
        AppUserDTO userDTO = new AppUserDTO();
        SkillCategoryDTO categoryDTO = new SkillCategoryDTO();

        detailsDTO.setId(92);
        detailsDTO.setFirstName("Dave");
        detailsDTO.setSurname("Burbidge");
        detailsDTO.setAddressFirstLine("Address line 1");
        detailsDTO.setAddressSecondLine("Address line 2");
        detailsDTO.setCounty("County 1");
        detailsDTO.setPostcode("Postcode 1");

        managerDetailsDTO.setId(93);
        managerDetailsDTO.setFirstName("Matt");
        managerDetailsDTO.setSurname("Morgan");
        managerDetailsDTO.setAddressFirstLine("Address line 1");
        managerDetailsDTO.setAddressSecondLine("Address line 2");
        managerDetailsDTO.setCounty("County 2");
        managerDetailsDTO.setPostcode("Postcode 2");

        userDTO.setId(103);
        userDTO.setUsername("Username 1");
        userDTO.setEmail("email1@email.com");
        userDTO.setPassword("password");

        managerUserDTO.setId(104);
        managerUserDTO.setUsername("Username 2");
        managerUserDTO.setEmail("email2@email.com");
        managerUserDTO.setPassword("password");

        //Set Manager
        managerDTO.setId(97);
        managerDTO.setDetails(managerDetailsDTO);
        managerDTO.setUser(managerUserDTO);

        categoryDTO.setId(78);
        categoryDTO.setDescription("Software");

        staffDTO.setId(2);
        staffDTO.setUser(userDTO);
        staffDTO.setDetails(detailsDTO);
        staffDTO.setManager(managerDTO);

        skillDTO.setId(45);
        skillDTO.setName("Java");
        skillDTO.setCategory(categoryDTO);

        staffDTOS.add(staffDTO);
        skillDTO.setStaffList(staffDTOS);

        skillDTOS.add(skillDTO);
        staffDTO.setSkills(skillDTOS);

        staffSkillDTO.setId(1);
        staffSkillDTO.setSkillStrength(6);
        staffSkillDTO.setSkill(skillDTO);
        staffSkillDTO.setStaff(staffDTO);
    }

    @Test
    public void test01_Convertor_Converts_Correctly(){
        StaffSkillIdDTO idDTO = convertor.convert(staffSkillDTO);

        assertEquals(staffSkillDTO.getId(), idDTO.getId());
        assertEquals(staffSkillDTO.getExpiryDate(), idDTO.getExpiryDate());
        assertEquals(staffSkillDTO.getSkillStrength(), idDTO.getSkillStrength());
        assertEquals(staffSkillDTO.getSkill().getId(), idDTO.getSkillId());
        assertEquals(staffSkillDTO.getStaff().getId(), idDTO.getStaffId());
    }
}
