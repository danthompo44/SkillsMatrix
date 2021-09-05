package com.university.skillsmatrix.services;

import com.university.skillsmatrix.convertor.details.DTOToPersonalDetailsConvertor;
import com.university.skillsmatrix.convertor.details.PersonalDetailsToDTOConvertor;
import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.entity.PersonalDetails;
import com.university.skillsmatrix.entity.SkillCategory;
import com.university.skillsmatrix.repository.PersonalDetailsRepository;
import com.university.skillsmatrix.service.DetailsService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DetailsServiceTest {
    @Mock private PersonalDetailsRepository repository;
    @Mock private PersonalDetailsToDTOConvertor detailsToDTOConvertor;
    @Mock private DTOToPersonalDetailsConvertor dtoToDetailsConvertor;

    private AutoCloseable closeable;

    @InjectMocks
    private DetailsService service;

    //Dummy Objects
    private final PersonalDetails d1 = new PersonalDetails();
    private final PersonalDetails d2 = new PersonalDetails();
    private PersonalDetailsDTO dto1 = new PersonalDetailsDTO();
    private PersonalDetailsDTO dto2 = new PersonalDetailsDTO();

    @BeforeEach
    void init(){
        closeable = openMocks(this);

        //Set Details 1
        d1.setId(92);
        d1.setFirstName("Dave");
        d1.setSurname("Burbidge");
        d1.setAddressFirstLine("Address line 1");
        d1.setAddressSecondLine("Address line 2");
        d1.setCounty("County 1");
        d1.setPostcode("Postcode 1");

        //Set Details 2
        d2.setId(93);
        d2.setFirstName("Matt");
        d2.setSurname("Morgan");
        d2.setAddressFirstLine("Address line 1");
        d2.setAddressSecondLine("Address line 2");
        d2.setCounty("County 2");
        d2.setPostcode("Postcode 2");

        dto1 = convertToDTO(d1);
        dto2 = convertToDTO(d2);
    }

    private PersonalDetailsDTO convertToDTO(PersonalDetails d){
        PersonalDetailsDTO dto = new PersonalDetailsDTO();
        dto.setId(d.getId());
        dto.setFirstName(d.getFirstName());
        dto.setSurname(d.getSurname());
        dto.setAddressFirstLine(d.getAddressFirstLine());
        dto.setAddressSecondLine(d.getAddressSecondLine());
        dto.setCounty(d.getCounty());
        dto.setPostcode(d.getPostcode());

        return dto;
    }
    @AfterEach
    void close() throws Exception{
        closeable.close();
    }

    @Test
    public void test01WhenGivenARequestForADetailsReturnExpected(){
        when(repository.findById(92L)).thenReturn(java.util.Optional.of(d1));

        //Details Convertor Mock Behaviour
        when(detailsToDTOConvertor.convert(d1)).thenReturn(dto1);

        PersonalDetailsDTO expectedDetails = dto1;

        PersonalDetailsDTO newDetails = service.getDetailsById(92L);

        //Assert Details are the same
        assertEquals(expectedDetails, newDetails);
    }

    @Test
    public void test02WhenGivenARequestToSaveDetailsTheyAreSaved(){
        List<PersonalDetails> requestedListOfDetails = new ArrayList<>();
        requestedListOfDetails.add(d1);
        when(repository.findAll()).thenReturn(requestedListOfDetails);

        //Details Convertor Mock Behaviour
        when(detailsToDTOConvertor.convert(d1)).thenReturn(dto1);
        when(detailsToDTOConvertor.convert(d2)).thenReturn(dto2);
        when(dtoToDetailsConvertor.convert(dto2)).thenReturn(d2);

        //Define List of expected response objects
        List<PersonalDetailsDTO> expectedListOfDetails = new ArrayList<>();
        expectedListOfDetails.add(dto1);

        List<PersonalDetailsDTO> newDetails = service.getAllDetails();

        //Assert sizes are the same
        assertEquals(newDetails.size(), requestedListOfDetails.size());

        //Check elements are the same
        for(int i = 0; i < newDetails.size(); i++){
            assertEquals(newDetails.get(i), expectedListOfDetails.get(i));
        }

        //Add Details to expected list
        requestedListOfDetails.add(d2);
        when(repository.findAll()).thenReturn(requestedListOfDetails);

        expectedListOfDetails.add(dto2);
        newDetails = service.getAllDetails();

        //Assert sizes are the same
        assertEquals(newDetails.size(), requestedListOfDetails.size());

        //Check elements are the same
        for(int i = 0; i < newDetails.size(); i++){
            assertEquals(newDetails.get(i), expectedListOfDetails.get(i));
        }
    }

    @Test
    public void test03WhenGivenARequestToRemoveDetailsTheyAreReplaced(){
        when(repository.findById(92L)).thenReturn(java.util.Optional.of(d1));

        //Details Convertor Mock Behaviour
        when(detailsToDTOConvertor.convert(d1)).thenReturn(dto1);

        PersonalDetailsDTO expectedDetails = dto1;

        PersonalDetailsDTO newDetails = service.getDetailsById(92L);

        //Assert Details are the same
        assertEquals(expectedDetails, newDetails);

        service.removeDetails(92L);

        newDetails = service.getDetailsById(92L);
        //Assert Details are the same
        assertEquals(expectedDetails, newDetails);
    }
}
