package com.university.skillsmatrix.repositories;

import com.university.skillsmatrix.entity.*;
import com.university.skillsmatrix.repository.StaffSkillRepository;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffSkillRepositoryTest {
    @Autowired
    private StaffSkillRepository repo;

    StaffSkill staffSkill = new StaffSkill();

    void init(){
        PersonalDetails details = new PersonalDetails();
        AppUser user = new AppUser();
        Manager manager = new Manager();
        PersonalDetails managerDetails = new PersonalDetails();
        AppUser managerUser = new AppUser();
        Staff staff = new Staff();
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

        staffSkill.setId(1);
        staffSkill.setStaff(staff);
        staffSkill.setSkill(skill1);
        staffSkill.setSkillStrength(8);
    }

    private void assertStaffSkill(StaffSkill expected, StaffSkill returned){
        assertEquals(expected.getId(), returned.getId());
        assertStaff(expected.getStaff(), returned.getStaff());
        assertSkill(expected.getSkill(), returned.getSkill());
        assertEquals(expected.getSkillStrength(), returned.getSkillStrength());
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

    private void assertStaffListSize(int expectedSize, Iterable<Staff> staff){
        long size = StreamSupport.stream(staff.spliterator(), false).count();
        assertEquals(expectedSize, size);
    }

    private void assertStaffSkillListSize(int expectedSize, Iterable<StaffSkill> staffSkills){
        long size = StreamSupport.stream(staffSkills.spliterator(), false).count();
        assertEquals(expectedSize, size);
    }

    private void assertSkill(Skill expected, Skill returned){
        assertEquals(expected.getId(), returned.getId());
        assertEquals(expected.getName(), returned.getName());

        //Assert Category
        assertEquals(expected.getCategory().getId(), returned.getCategory().getId());
        assertEquals(expected.getCategory().getDescription(), returned.getCategory().getDescription());
    }

    private void updateStaffSkill(){
        staffSkill.setSkillStrength(2);
    }

    @Test
    public void test_01WhenGivenARequestForAllStaffSkillsReturnAll(){
        assertStaffSkillListSize(8, repo.findAll());
    }

    @Test
    public void test_02WhenGivenARequestToRetrieveStaffSkillByIdItIsReturned(){
        init();
        assertStaffSkill(staffSkill, repo.findById(staffSkill.getId()).get());
    }

    @Test
    public void test_03WhenGivenARequestToUpdateAStaffSkillItIsUpdated(){
        init();
        updateStaffSkill();
        assertStaffSkill(staffSkill, repo.save(staffSkill));
    }

    @Test
    public void test_04WhenGivenARequestToDeleteAStaffSkillItIsDeleted(){
        init();
        repo.delete(staffSkill);
        assertEquals(repo.findById(staffSkill.getId()), Optional.empty());
    }

    @Test
    public void test_05WhenGivenARequestToFindAllByStaffIdReturnAsExpected(){
        init();
        assertStaffSkillListSize(2, repo.findAllByStaffId(staffSkill.getStaff().getId()));
    }
}
