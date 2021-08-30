package com.university.skillsmatrix.convertors.details;

import com.university.skillsmatrix.convertor.details.PersonalDetailsToDTOConvertor;
import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import com.university.skillsmatrix.entity.PersonalDetails;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetailsToDTOConvertorTest {
    private PersonalDetailsToDTOConvertor convertor;
    private PersonalDetails details;
    private PersonalDetailsDTO detailsDTO;

    @Before
    public void init(){
        convertor = new PersonalDetailsToDTOConvertor();
        details = new PersonalDetails();
        detailsDTO = new PersonalDetailsDTO();

        details.setId(5);
        details.setFirstName("First Name");
        details.setSurname("Surname");
        details.setAddressFirstLine("Address First Line");
        details.setAddressSecondLine("Address Second Line");
        details.setCounty("County");
        details.setPostcode("Postcode");
    }

    @Test
    public void test01WhenGivenAPersonalDetailsConvertorReturnsPersonalDetailsDTO(){
        detailsDTO = convertor.convert(details);
        assertEquals(details.getId(), detailsDTO.getId());
        assertEquals(details.getFirstName(), detailsDTO.getFirstName());
        assertEquals(details.getSurname(), detailsDTO.getSurname());
        assertEquals(details.getAddressFirstLine(), detailsDTO.getAddressFirstLine());
        assertEquals(details.getAddressSecondLine(), detailsDTO.getAddressSecondLine());
        assertEquals(details.getCounty(), detailsDTO.getCounty());
        assertEquals(details.getPostcode(), detailsDTO.getPostcode());
    }
}
