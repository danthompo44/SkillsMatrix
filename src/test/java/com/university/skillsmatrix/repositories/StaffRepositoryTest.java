package com.university.skillsmatrix.repositories;

import com.university.skillsmatrix.entity.*;
import com.university.skillsmatrix.repository.StaffRepository;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffRepositoryTest {
    @Autowired
    private StaffRepository repo;

    private Staff staff = new Staff();

    void init(){
        PersonalDetails details = new PersonalDetails();
        AppUser user = new AppUser();
        Manager manager = new Manager();
        PersonalDetails managerDetails = new PersonalDetails();
        AppUser managerUser = new AppUser();
        SkillCategory category = new SkillCategory();
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        Role userRole = new Role();
        Role managerRole = new Role();

        //Set Details As database
        details.setId(2);
        details.setFirstName("Matt");
        details.setSurname("Morgan");
        details.setAddressFirstLine("15 Fulmar Road");
        details.setAddressSecondLine("Weston-Super-Mare");
        details.setCounty("Somerset");
        details.setPostcode("BS22 6EJ");

        //Set user role
        userRole.setId(1);
        userRole.setType("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        //Set User as database
        user.setId(1);
        user.setUsername("user");
        user.setEmail("matt@morgan.com");
        user.setRoles(userRoles);

        //Set Manager Details as database
        managerDetails.setId(1);
        managerDetails.setFirstName("Dan");
        managerDetails.setSurname("Thompson");
        managerDetails.setAddressFirstLine("44 Garsdale Road");
        managerDetails.setAddressSecondLine("Weston-Super-Mare");
        managerDetails.setCounty("Somerset");
        managerDetails.setPostcode("BS22 8PU");

        //Set manager Role
        managerRole.setId(2);
        managerRole.setType("ROLE_ADMIN");
        List<Role> managerRoles = new ArrayList<>();
        managerRoles.add(managerRole);

        //Set manager user as database
        managerUser.setId(4);
        managerUser.setUsername("admin");
        managerUser.setEmail("dan@thompson.co.uk");
        managerUser.setRoles(managerRoles);

        //Set Manager as database
        manager.setId(1);
        manager.setDetails(managerDetails);
        manager.setUser(managerUser);

        //Set Category as database
        category.setId(1);
        category.setDescription("Software");

        //Set skills as database
        skill1.setId(1);
        skill1.setName("Java");
        skill1.setCategory(category);
        skill2.setId(2);
        skill2.setName("Automation");
        skill2.setCategory(category);

        //Add skills to array list
        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        //Set staff as database
        staff.setId(1);
        staff.setDetails(details);
        staff.setUser(user);
        staff.setManager(manager);
        staff.setSkills(skills);
    }


    private void assertStaff(Staff expected, Staff returned){
        assertEquals(expected.getId(), returned.getId());
        assertDetails(expected.getDetails(), returned.getDetails());
        assertManager(expected.getManager(), returned.getManager());
        assertUser(expected.getUser(), returned.getUser());
        assertSkillListSize(expected.getSkills(), returned.getSkills());
    }

    private void assertDetails(PersonalDetails expected, PersonalDetails returned){
        assertEquals(expected.getId(), returned.getId());
        assertEquals(expected.getFirstName(), returned.getFirstName());
        assertEquals(expected.getSurname(), returned.getSurname());
        assertEquals(expected.getAddressFirstLine(), returned.getAddressFirstLine());
        assertEquals(expected.getAddressSecondLine(), returned.getAddressSecondLine());
        assertEquals(expected.getCounty(), returned.getCounty());
        assertEquals(expected.getPostcode(), returned.getPostcode());
    }

    private void assertManager(Manager expected, Manager returned){
        assertEquals(expected.getId(), returned.getId());
        assertDetails(expected.getDetails(), returned.getDetails());
        assertUser(expected.getUser(), returned.getUser());
    }

    private void assertUser(AppUser expected, AppUser returned){
        assertEquals(expected.getId(), returned.getId());
        assertEquals(expected.getUsername(), returned.getUsername());
        assertEquals(expected.getEmail(), returned.getEmail());
        assertRoleListSize(expected.getRoles(), returned.getRoles());
        //Do not assert password
    }

    private void assertRoleListSize(List<Role> expected, List<Role> returned){
        assertEquals(expected.size(), returned.size());
    }

    private void assertSkillListSize(List<Skill> expected, List<Skill> returned){
        assertEquals(expected.size(), returned.size());
    }

    private void assertListIsCorrectSize(int expectedSize, Iterable<Staff> staff){
        long size = StreamSupport.stream(staff.spliterator(), false).count();
        assertEquals(expectedSize, size);
    }

    @Test
    public void test_01WhenGivenARequestForAllStaffSizeOfListIsAsExpected(){
        //Expected size is 4
        assertListIsCorrectSize(4,repo.findAll());
    }

    @Test
    public void test02_WhenGivenARequestToRetrieveAStaffByItsIdReturnAsExpected(){
        init();
        assertStaff(staff, repo.findStaffByUserId(staff.getId()));
    }

    @Test
    public void test03_WhenGivenARequestToRetrieveAStaffMemberByTheirUserIdReturnAsExpected(){
        init();
        assertStaff(staff, repo.findStaffByUserId(staff.getUser().getId()));

    }

    @Test
    public void test04_WhenGivenARequestToRetrieveStaffByAManagersIdReturnAsExpected(){
        init();
        assertListIsCorrectSize(2, repo.findStaffByManagerId(staff.getManager().getId()));
    }

    @Test
    public void test05_WhenGivenARequestToRetrieveStaffBySkillsIdReturnAsExpected(){
        init();
        assertListIsCorrectSize(3, repo.findStaffsBySkillsId(2L));
    }
}
