package com.university.skillsmatrix.convertors.staffSkill;

import com.university.skillsmatrix.convertor.staffSkill.StaffSkillToDTOConvertor;
import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffSkillToDTOConvertorTest {
    private StaffSkillToDTOConvertor convertor;
    private StaffSkill staffSkill;
    private final List<Staff> staffList = new ArrayList<>();
    private final List<Skill> skillList = new ArrayList<>();

    @Before
    public void init(){
        convertor = new StaffSkillToDTOConvertor();
        staffSkill = new StaffSkill();
        Staff staff = new Staff();
        Skill skill = new Skill();
        Manager manager = new Manager();
        PersonalDetails details = new PersonalDetails();
        PersonalDetails managerDetails = new PersonalDetails();
        AppUser managerUser = new AppUser();
        AppUser user = new AppUser();
        SkillCategory category = new SkillCategory();

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

        staffSkill.setId(1);
        staffSkill.setSkillStrength(6);
        staffSkill.setSkill(skill);
        staffSkill.setStaff(staff);
    }

    @Test
    public void test01_Convertor_Converts_As_Expected(){
        StaffSkillDTO dto = convertor.convert(staffSkill);

        assertEquals(staffSkill.getId(), dto.getId());
        assertEquals(staffSkill.getExpiryDate(), dto.getExpiryDate());
        assertEquals(staffSkill.getSkillStrength(), dto.getSkillStrength());

        //Assert Skill Info
        assertEquals(staffSkill.getSkill().getId(), dto.getSkill().getId());
        assertEquals(staffSkill.getSkill().getName(), dto.getSkill().getName());
        assertEquals(staffSkill.getSkill().getCategory().getId(), dto.getSkill().getCategory().getId());
        assertEquals(staffSkill.getSkill().getCategory().getDescription(), dto.getSkill().getCategory().getDescription());

        //Assert Staff Info
        assertEquals(staffSkill.getStaff().getId(), dto.getStaff().getId());

        //Assert Staff Details
        assertEquals(staffSkill.getStaff().getDetails().getId(), dto.getStaff().getDetails().getId());
        assertEquals(staffSkill.getStaff().getDetails().getFirstName(), dto.getStaff().getDetails().getFirstName());
        assertEquals(staffSkill.getStaff().getDetails().getSurname(), dto.getStaff().getDetails().getSurname());
        assertEquals(staffSkill.getStaff().getDetails().getAddressFirstLine(), dto.getStaff().getDetails().getAddressFirstLine());
        assertEquals(staffSkill.getStaff().getDetails().getAddressSecondLine(), dto.getStaff().getDetails().getAddressSecondLine());
        assertEquals(staffSkill.getStaff().getDetails().getCounty(), dto.getStaff().getDetails().getCounty());
        assertEquals(staffSkill.getStaff().getDetails().getPostcode(), dto.getStaff().getDetails().getPostcode());

        //Assert Staff User
        assertEquals(staffSkill.getStaff().getUser().getId(), dto.getStaff().getUser().getId());
        assertEquals(staffSkill.getStaff().getUser().getEmail(), dto.getStaff().getUser().getEmail());
        assertEquals(staffSkill.getStaff().getUser().getUsername(), dto.getStaff().getUser().getUsername());
        assertEquals(staffSkill.getStaff().getUser().getPassword(), dto.getStaff().getUser().getPassword());

        //Assert Staff Manager
        assertEquals(staffSkill.getStaff().getManager().getId(), dto.getStaff().getManager().getId());
        assertEquals(staffSkill.getStaff().getManager().getUser().getId(), dto.getStaff().getManager().getUser().getId());
        assertEquals(staffSkill.getStaff().getManager().getUser().getUsername(), dto.getStaff().getManager().getUser().getUsername());
        assertEquals(staffSkill.getStaff().getManager().getUser().getEmail(), dto.getStaff().getManager().getUser().getEmail());
        assertEquals(staffSkill.getStaff().getManager().getUser().getPassword(), dto.getStaff().getManager().getUser().getPassword());

        assertEquals(staffSkill.getStaff().getManager().getDetails().getId(), dto.getStaff().getManager().getDetails().getId());
        assertEquals(staffSkill.getStaff().getManager().getDetails().getFirstName(), dto.getStaff().getManager().getDetails().getFirstName());
        assertEquals(staffSkill.getStaff().getManager().getDetails().getSurname(), dto.getStaff().getManager().getDetails().getSurname());
        assertEquals(staffSkill.getStaff().getManager().getDetails().getAddressFirstLine(), dto.getStaff().getManager().getDetails().getAddressFirstLine());
        assertEquals(staffSkill.getStaff().getManager().getDetails().getAddressSecondLine(), dto.getStaff().getManager().getDetails().getAddressSecondLine());
        assertEquals(staffSkill.getStaff().getManager().getDetails().getCounty(), dto.getStaff().getManager().getDetails().getCounty());
        assertEquals(staffSkill.getStaff().getManager().getDetails().getPostcode(), dto.getStaff().getManager().getDetails().getPostcode());

        assertEquals(staffSkill.getStaff().getSkills().size(), dto.getStaff().getSkills().size());
    }
}
