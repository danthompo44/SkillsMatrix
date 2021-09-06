package com.university.skillsmatrix.convertors.staffSkill;

import com.university.skillsmatrix.convertor.staffSkill.DTOToStaffSkillConvertor;
import com.university.skillsmatrix.convertor.staffSkill.StaffSkillToDTOConvertor;
import com.university.skillsmatrix.domain.*;
import com.university.skillsmatrix.entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOToStaffSkillConvertorTest {
    private DTOToStaffSkillConvertor convertor;
    private StaffSkillDTO staffSkillDTO;
    private final List<StaffDTO> staffDTOS = new ArrayList<>();
    private final List<SkillDTO> skillDTOS = new ArrayList<>();

    @Before
    public void init(){
        convertor = new DTOToStaffSkillConvertor();
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
    public void test01_Convertor_Converts_As_Expected(){
        StaffSkill staffSkill = convertor.convert(staffSkillDTO);

        assertEquals(staffSkillDTO.getId(), staffSkill.getId());
        assertEquals(staffSkillDTO.getExpiryDate(), staffSkill.getExpiryDate());
        assertEquals(staffSkillDTO.getSkillStrength(), staffSkill.getSkillStrength());

        //Assert Skill Info
        assertEquals(staffSkillDTO.getSkill().getId(), staffSkill.getSkill().getId());
        assertEquals(staffSkillDTO.getSkill().getName(), staffSkill.getSkill().getName());
        assertEquals(staffSkillDTO.getSkill().getCategory().getId(), staffSkill.getSkill().getCategory().getId());
        assertEquals(staffSkillDTO.getSkill().getCategory().getDescription(), staffSkill.getSkill().getCategory().getDescription());

        //Assert Staff Info
        assertEquals(staffSkillDTO.getStaff().getId(), staffSkill.getStaff().getId());

        //Assert Staff Details
        assertEquals(staffSkillDTO.getStaff().getDetails().getId(), staffSkill.getStaff().getDetails().getId());
        assertEquals(staffSkillDTO.getStaff().getDetails().getFirstName(), staffSkill.getStaff().getDetails().getFirstName());
        assertEquals(staffSkillDTO.getStaff().getDetails().getSurname(), staffSkill.getStaff().getDetails().getSurname());
        assertEquals(staffSkillDTO.getStaff().getDetails().getAddressFirstLine(), staffSkill.getStaff().getDetails().getAddressFirstLine());
        assertEquals(staffSkillDTO.getStaff().getDetails().getAddressSecondLine(), staffSkill.getStaff().getDetails().getAddressSecondLine());
        assertEquals(staffSkillDTO.getStaff().getDetails().getCounty(), staffSkill.getStaff().getDetails().getCounty());
        assertEquals(staffSkillDTO.getStaff().getDetails().getPostcode(), staffSkill.getStaff().getDetails().getPostcode());

        //Assert Staff User
        assertEquals(staffSkillDTO.getStaff().getUser().getId(), staffSkill.getStaff().getUser().getId());
        assertEquals(staffSkillDTO.getStaff().getUser().getEmail(), staffSkill.getStaff().getUser().getEmail());
        assertEquals(staffSkillDTO.getStaff().getUser().getUsername(), staffSkill.getStaff().getUser().getUsername());
        assertEquals(staffSkillDTO.getStaff().getUser().getPassword(), staffSkill.getStaff().getUser().getPassword());

        //Assert Staff Manager
        assertEquals(staffSkillDTO.getStaff().getManager().getId(), staffSkill.getStaff().getManager().getId());
        assertEquals(staffSkillDTO.getStaff().getManager().getUser().getId(), staffSkill.getStaff().getManager().getUser().getId());
        assertEquals(staffSkillDTO.getStaff().getManager().getUser().getUsername(), staffSkill.getStaff().getManager().getUser().getUsername());
        assertEquals(staffSkillDTO.getStaff().getManager().getUser().getEmail(), staffSkill.getStaff().getManager().getUser().getEmail());
        assertEquals(staffSkillDTO.getStaff().getManager().getUser().getPassword(), staffSkill.getStaff().getManager().getUser().getPassword());

        assertEquals(staffSkillDTO.getStaff().getManager().getDetails().getId(), staffSkill.getStaff().getManager().getDetails().getId());
        assertEquals(staffSkillDTO.getStaff().getManager().getDetails().getFirstName(), staffSkill.getStaff().getManager().getDetails().getFirstName());
        assertEquals(staffSkillDTO.getStaff().getManager().getDetails().getSurname(), staffSkill.getStaff().getManager().getDetails().getSurname());
        assertEquals(staffSkillDTO.getStaff().getManager().getDetails().getAddressFirstLine(), staffSkill.getStaff().getManager().getDetails().getAddressFirstLine());
        assertEquals(staffSkillDTO.getStaff().getManager().getDetails().getAddressSecondLine(), staffSkill.getStaff().getManager().getDetails().getAddressSecondLine());
        assertEquals(staffSkillDTO.getStaff().getManager().getDetails().getCounty(), staffSkill.getStaff().getManager().getDetails().getCounty());
        assertEquals(staffSkillDTO.getStaff().getManager().getDetails().getPostcode(), staffSkill.getStaff().getManager().getDetails().getPostcode());

        assertEquals(staffSkillDTO.getStaff().getSkills().size(), staffSkill.getStaff().getSkills().size());
    }
}
