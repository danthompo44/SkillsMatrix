package com.university.skillsmatrix.convertors.details;

import com.university.skillsmatrix.convertor.details.DTOToPersonalDetailsConvertor;
import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import com.university.skillsmatrix.entity.PersonalDetails;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DTOToDetailsConvertorTest {
    private DTOToPersonalDetailsConvertor convertor;
    private PersonalDetailsDTO detailsDTO;
    private PersonalDetails details;

    @Before
    public void init(){
        convertor = new DTOToPersonalDetailsConvertor();
        detailsDTO = new PersonalDetailsDTO();
        details = new PersonalDetails();

        detailsDTO.setId(5);
        detailsDTO.setFirstName("First Name");
        detailsDTO.setSurname("Surname");
        detailsDTO.setAddressFirstLine("Address First Line");
        detailsDTO.setAddressSecondLine("Address Second Line");
        detailsDTO.setCounty("County");
        detailsDTO.setPostcode("Postcode");
    }

    @Test
    public void test01WhenGivenADetailsDTOConvertorReturnsPersonalDetails(){
        details = convertor.convert(detailsDTO);
        assertEquals(detailsDTO.getId(), details.getId());
        assertEquals(detailsDTO.getFirstName(), details.getFirstName());
        assertEquals(detailsDTO.getAddressFirstLine(), details.getAddressFirstLine());
        assertEquals(detailsDTO.getAddressSecondLine(), details.getAddressSecondLine());
        assertEquals(detailsDTO.getCounty(), details.getCounty());
        assertEquals(detailsDTO.getPostcode(), details.getPostcode());
    }
}
