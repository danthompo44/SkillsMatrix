package com.university.skillsmatrix.DTO;

import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonalDetailsDTOValidatorTest {
    private Validator validator;
    private PersonalDetailsDTO detailsDTO;
    private final String tooSmallString = "";
    private final String tooLargeString = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    private final String validString = "Valid";

    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        detailsDTO  = new PersonalDetailsDTO();
        detailsDTO.setId(1);
    }

    private void testFirstName(PersonalDetailsDTO d, boolean isValid){
        detailsDTO.setSurname("surname");
        detailsDTO.setAddressFirstLine("Address 1");
        detailsDTO.setAddressSecondLine("Address 2");
        detailsDTO.setCounty("county");
        detailsDTO.setPostcode("postcode");
        assertPersonalDetails(d, isValid);
    }

    private void testSurname(PersonalDetailsDTO d, boolean isValid){
        detailsDTO.setFirstName("first name");
        detailsDTO.setAddressFirstLine("Address 1");
        detailsDTO.setAddressSecondLine("Address 2");
        detailsDTO.setCounty("county");
        detailsDTO.setPostcode("postcode");
        assertPersonalDetails(d, isValid);
    }

    private void testAddressOne(PersonalDetailsDTO d, boolean isValid){
        detailsDTO.setFirstName("first name");
        detailsDTO.setSurname("surname");
        detailsDTO.setAddressSecondLine("Address 2");
        detailsDTO.setCounty("county");
        detailsDTO.setPostcode("postcode");
        assertPersonalDetails(d, isValid);
    }

    private void testAddressTwo(PersonalDetailsDTO d, boolean isValid){
        detailsDTO.setFirstName("first name");
        detailsDTO.setSurname("surname");
        detailsDTO.setAddressFirstLine("Address 1");
        detailsDTO.setCounty("county");
        detailsDTO.setPostcode("postcode");
        assertPersonalDetails(d, isValid);
    }

    private void testCounty(PersonalDetailsDTO d, boolean isValid){
        detailsDTO.setFirstName("first name");
        detailsDTO.setSurname("surname");
        detailsDTO.setAddressFirstLine("Address 1");
        detailsDTO.setAddressSecondLine("Address 2");
        detailsDTO.setPostcode("postcode");
        assertPersonalDetails(d, isValid);
    }

    private void testPostcode(PersonalDetailsDTO d, boolean isValid){
        detailsDTO.setFirstName("first name");
        detailsDTO.setSurname("surname");
        detailsDTO.setAddressFirstLine("Address 1");
        detailsDTO.setAddressSecondLine("Address 2");
        detailsDTO.setCounty("county");
        assertPersonalDetails(d, isValid);

    }

    private void assertPersonalDetails(PersonalDetailsDTO d, boolean isValid){
        Set<ConstraintViolation<PersonalDetailsDTO>> violations = validator.validate(d);
        assertEquals(violations.isEmpty(), isValid);
    }

    @Test
    public void test01FirstNameIsTooLarge(){
        detailsDTO.setFirstName(tooLargeString);
        testFirstName(detailsDTO, false);
    }
    @Test
    public void test02FirstNameIsTooSmall(){
        detailsDTO.setFirstName(tooSmallString);
        testFirstName(detailsDTO, false);
    }
    @Test
    public void test03FirstNameIsValid(){
        detailsDTO.setFirstName(validString);
        testFirstName(detailsDTO, true);
    }
    @Test
    public void test04SurnameIsTooLarge(){
        detailsDTO.setSurname(tooLargeString);
        testSurname(detailsDTO, false);
    }
    @Test
    public void test05SurnameIsTooSmall(){
        detailsDTO.setSurname(tooSmallString);
        testSurname(detailsDTO, false);
    }
    @Test
    public void test06SurnameIsValid(){
        detailsDTO.setSurname(validString);
        testSurname(detailsDTO, true);
    }
    @Test
    public void test07AddressFirstLineIsTooLarge(){
        detailsDTO.setAddressFirstLine(tooLargeString);
        testAddressOne(detailsDTO, false);
    }
    @Test
    public void test08AddressFirstLineIsTooSmall(){
        detailsDTO.setAddressFirstLine(tooSmallString);
        testAddressOne(detailsDTO, false);
    }
    @Test
    public void test09AddressFirstLineIsValid(){
        detailsDTO.setAddressFirstLine(validString);
        testAddressOne(detailsDTO, true);
    }
    @Test
    public void test10AddressSecondLineIsTooLarge(){
        detailsDTO.setAddressSecondLine(tooLargeString);
        testAddressTwo(detailsDTO, false);
    }
    @Test
    public void test11AddressSecondLineIsTooSmall(){
        detailsDTO.setAddressSecondLine(tooSmallString);
        testAddressTwo(detailsDTO, false);
    }
    @Test
    public void test12AddressSecondLineIsValid(){
        detailsDTO.setAddressSecondLine(validString);
        testAddressTwo(detailsDTO, true);
    }
    @Test
    public void test13CountyIsTooLarge(){
        detailsDTO.setCounty(tooLargeString);
        testCounty(detailsDTO, false);
    }
    @Test
    public void test14CountyIsTooSmall(){
        detailsDTO.setCounty(tooSmallString);
        testCounty(detailsDTO, false);
    }
    @Test
    public void test15CountyIsValid(){
        detailsDTO.setCounty(validString);
        testCounty(detailsDTO, true);
    }
    @Test
    public void test16PostCodeIsTooLarge(){
        detailsDTO.setPostcode(tooLargeString);
        testPostcode(detailsDTO, false);
    }
    @Test
    public void test17PostCodeIsTooSmall(){
        detailsDTO.setPostcode(tooSmallString);
        testPostcode(detailsDTO, false);
    }
    @Test
    public void test18PostCodeIsValid(){
        detailsDTO.setPostcode(validString);
        testPostcode(detailsDTO, true);
    }

    //Null tests
    @Test
    public void test19FirstNameIsNull(){
        testFirstName(detailsDTO, false);
    }
    @Test
    public void test20SurnameIsNull(){
        testSurname(detailsDTO, false);
    }
    @Test
    public void test21AddressFirstLineIsNull(){
        testAddressOne(detailsDTO, false);
    }
    @Test
    public void test22AddressSecondLineIsNull(){
        testAddressTwo(detailsDTO, false);
    }
    @Test
    public void test23CountyIsNull(){
        testCounty(detailsDTO, false);
    }
    @Test
    public void test24PostCodeIsNull(){
        testPostcode(detailsDTO, false);
    }
}
