package com.university.skillsmatrix.convertors.staffSkill;

import com.university.skillsmatrix.convertor.staffSkill.IdDTOToStaffSkillDTOConvertor;
import com.university.skillsmatrix.domain.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdDTOToStaffSkillDTO {
    private IdDTOToStaffSkillDTOConvertor convertor;
    private StaffSkillIdDTO idDTO;
    private final List<StaffDTO> staffList = new ArrayList<>();
    private final List<SkillDTO> skillList = new ArrayList<>();
    private StaffDTO staff;
    private SkillDTO skill;

    @Before
    public void init(){
        convertor = new IdDTOToStaffSkillDTOConvertor();
        idDTO = new StaffSkillIdDTO();
        staff = new StaffDTO();
        PersonalDetailsDTO details = new PersonalDetailsDTO();
        AppUserDTO user = new AppUserDTO();
        ManagerDTO manager = new ManagerDTO();
        PersonalDetailsDTO managerDetails = new PersonalDetailsDTO();
        AppUserDTO managerUser = new AppUserDTO();
        skill = new SkillDTO();
        SkillCategoryDTO category = new SkillCategoryDTO();

        idDTO.setId(67);
        idDTO.setExpiryDate(new Date());
        idDTO.setSkillStrength(6);
        idDTO.setSkillId(33L);
        idDTO.setStaffId(89L);

        details.setId(92);
        details.setFirstName("Dave");
        details.setSurname("Burbidge");
        details.setAddressFirstLine("Address line 1");
        details.setAddressSecondLine("Address line 2");
        details.setCounty("County 1");
        details.setPostcode("Postcode 1");

        managerDetails.setId(93);
        managerDetails.setFirstName("Matt");
        managerDetails.setSurname("Morgan");
        managerDetails.setAddressFirstLine("Address line 1");
        managerDetails.setAddressSecondLine("Address line 2");
        managerDetails.setCounty("County 2");
        managerDetails.setPostcode("Postcode 2");

        user.setId(103);
        user.setUsername("Username 1");
        user.setEmail("email1@email.com");
        user.setPassword("password");

        managerUser.setId(104);
        managerUser.setUsername("Username 2");
        managerUser.setEmail("email2@email.com");
        managerUser.setPassword("password");

        //Set Manager
        manager.setId(97);
        manager.setDetails(managerDetails);
        manager.setUser(managerUser);

        category.setId(78);
        category.setDescription("Software");

        staff.setId(2);
        staff.setUser(user);
        staff.setDetails(details);
        staff.setManager(manager);

        skill.setId(45);
        skill.setName("Java");
        skill.setCategory(category);

        staffList.add(staff);
        skill.setStaffList(staffList);

        skillList.add(skill);
        staff.setSkills(skillList);
    }

    @Test
    public void test01_Convert_Converts_Correctly(){
        StaffSkillDTO dto = convertor.convert(idDTO, staff, skill);

        assertEquals(idDTO.getId(), dto.getId());
        assertEquals(idDTO.getExpiryDate(), dto.getExpiryDate());
        assertEquals(idDTO.getSkillStrength(), dto.getSkillStrength());

        //Assert Skill Info
        assertEquals(skill.getId(), dto.getSkill().getId());
        assertEquals(skill.getName(), dto.getSkill().getName());
        assertEquals(skill.getCategory().getId(), dto.getSkill().getCategory().getId());
        assertEquals(skill.getCategory().getDescription(), dto.getSkill().getCategory().getDescription());

        //Assert Staff Info
        assertEquals(staff.getId(), dto.getStaff().getId());

        //Assert Staff Details
        assertEquals(staff.getDetails().getId(), dto.getStaff().getDetails().getId());
        assertEquals(staff.getDetails().getFirstName(), dto.getStaff().getDetails().getFirstName());
        assertEquals(staff.getDetails().getSurname(), dto.getStaff().getDetails().getSurname());
        assertEquals(staff.getDetails().getAddressFirstLine(), dto.getStaff().getDetails().getAddressFirstLine());
        assertEquals(staff.getDetails().getAddressSecondLine(), dto.getStaff().getDetails().getAddressSecondLine());
        assertEquals(staff.getDetails().getCounty(), dto.getStaff().getDetails().getCounty());
        assertEquals(staff.getDetails().getPostcode(), dto.getStaff().getDetails().getPostcode());

        //Assert Staff User
        assertEquals(staff.getUser().getId(), dto.getStaff().getUser().getId());
        assertEquals(staff.getUser().getEmail(), dto.getStaff().getUser().getEmail());
        assertEquals(staff.getUser().getUsername(), dto.getStaff().getUser().getUsername());
        assertEquals(staff.getUser().getPassword(), dto.getStaff().getUser().getPassword());

        //Assert Staff Manager
        assertEquals(staff.getManager().getId(), dto.getStaff().getManager().getId());
        assertEquals(staff.getManager().getUser().getId(), dto.getStaff().getManager().getUser().getId());
        assertEquals(staff.getManager().getUser().getUsername(), dto.getStaff().getManager().getUser().getUsername());
        assertEquals(staff.getManager().getUser().getEmail(), dto.getStaff().getManager().getUser().getEmail());
        assertEquals(staff.getManager().getUser().getPassword(), dto.getStaff().getManager().getUser().getPassword());

        assertEquals(staff.getManager().getDetails().getId(), dto.getStaff().getManager().getDetails().getId());
        assertEquals(staff.getManager().getDetails().getFirstName(), dto.getStaff().getManager().getDetails().getFirstName());
        assertEquals(staff.getManager().getDetails().getSurname(), dto.getStaff().getManager().getDetails().getSurname());
        assertEquals(staff.getManager().getDetails().getAddressFirstLine(), dto.getStaff().getManager().getDetails().getAddressFirstLine());
        assertEquals(staff.getManager().getDetails().getAddressSecondLine(), dto.getStaff().getManager().getDetails().getAddressSecondLine());
        assertEquals(staff.getManager().getDetails().getCounty(), dto.getStaff().getManager().getDetails().getCounty());
        assertEquals(staff.getManager().getDetails().getPostcode(), dto.getStaff().getManager().getDetails().getPostcode());

        assertEquals(staff.getSkills().size(), dto.getStaff().getSkills().size());
    }
}
