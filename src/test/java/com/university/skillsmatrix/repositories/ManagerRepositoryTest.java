package com.university.skillsmatrix.repositories;

import com.university.skillsmatrix.entity.AppUser;
import com.university.skillsmatrix.entity.Manager;
import com.university.skillsmatrix.entity.PersonalDetails;
import com.university.skillsmatrix.repository.ManagerRepository;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DataJpaTest
public class ManagerRepositoryTest {
    @Autowired
    private ManagerRepository repository;

    private Manager manager = new Manager();
    AppUser user = new AppUser();
    PersonalDetails details = new PersonalDetails();

    private void init() {
        //Set Details the same as database
        details.setId(1);
        details.setFirstName("Dan");
        details.setSurname("Thompson");
        details.setAddressFirstLine("44 Garsdale Road");
        details.setAddressSecondLine("Weston-Super-Mare");
        details.setCounty("Somerset");
        details.setPostcode("BS22 8PU");

        //Set user
        user.setId(4);
        user.setUsername("admin");
        user.setEmail("dan@thompson.co.uk");

        //Set manager
        manager.setId(1);
        manager.setUser(user);
        manager.setDetails(details);

    }

    @Test
    public void test01WhenFindManagerByUserIdThenReturnManager(){
        init();
        //Assert Id
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getId(), manager.getId());

        //Assert User - Ignoring Password
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getUser().getId(), manager.getUser().getId());
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getUser().getUsername(), manager.getUser().getUsername());
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getUser().getEmail(), manager.getUser().getEmail());

        //Asert Details
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getDetails().getId(), manager.getDetails().getId());
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getDetails().getFirstName(), manager.getDetails().getFirstName());
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getDetails().getSurname(), manager.getDetails().getSurname());
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getDetails().getAddressFirstLine(), manager.getDetails().getAddressFirstLine());
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getDetails().getAddressSecondLine(), manager.getDetails().getAddressSecondLine());
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getDetails().getCounty(), manager.getDetails().getCounty());
        assertEquals(repository.findManagerByUserId(manager.getUser().getId()).getDetails().getPostcode(), manager.getDetails().getPostcode());

    }
}
