package com.university.skillsmatrix.repositories;

import com.university.skillsmatrix.entity.PersonalDetails;
import com.university.skillsmatrix.repository.PersonalDetailsRepository;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DataJpaTest
public class DetailsRepositoryTest {
    @Autowired
    private PersonalDetailsRepository repo;

    private PersonalDetails details = new PersonalDetails();

    void init(){
        //Set Details the same as database
        details.setId(1);
        details.setFirstName("Dan");
        details.setSurname("Thompson");
        details.setAddressFirstLine("44 Garsdale Road");
        details.setAddressSecondLine("Weston-Super-Mare");
        details.setCounty("Somerset");
        details.setPostcode("BS22 8PU");
    }

    @Test
    public void test01WhenGivenARequestForGetDetailsByIdReturnDetails(){
        init();
        assertDetails(repo.findById(details.getId()).get(), (details));
    }

    @Test
    public void test02WhenGivenARequestToUpdateDetailsUpdateDetails(){
        init();
        updateTestDetails();

        assertDetails(repo.save(details), details);
    }

    private void updateTestDetails(){
        details.setFirstName("New First Name");
        details.setSurname("New surame");
        details.setAddressFirstLine("New Address First Line");
        details.setAddressSecondLine("New Address second line");
        details.setCounty("New County");
        details.setPostcode("New Postcode");
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
}
