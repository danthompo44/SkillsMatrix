package com.university.skillsmatrix.services;

import com.university.skillsmatrix.convertor.manager.ManagerToDTOConvertor;
import com.university.skillsmatrix.domain.AppUserDTO;
import com.university.skillsmatrix.domain.ManagerDTO;
import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import com.university.skillsmatrix.entity.AppUser;
import com.university.skillsmatrix.entity.Manager;
import com.university.skillsmatrix.entity.PersonalDetails;
import com.university.skillsmatrix.repository.ManagerRepository;
import com.university.skillsmatrix.service.ManagerService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerServiceTest {
    @Mock private ManagerRepository repo;
    @Mock private ManagerToDTOConvertor convertor;

    private AutoCloseable closeable;

    @InjectMocks
    private ManagerService service;

    private final Manager m1 = new Manager();
    private final AppUser user = new AppUser();
    private final PersonalDetails details = new PersonalDetails();
    private ManagerDTO dto1 = new ManagerDTO();

    @BeforeEach
    void init(){
        closeable = openMocks(this);

        //Set Details
        details.setId(92);
        details.setFirstName("Dave");
        details.setSurname("Burbidge");
        details.setAddressFirstLine("Address line 1");
        details.setAddressSecondLine("Address line 2");
        details.setCounty("County 1");
        details.setPostcode("Postcode 1");

        //Set AppUser
        user.setId(103);
        user.setUsername("Username 1");
        user.setEmail("email1@email.com");
        user.setPassword("password");

        m1.setId(34L);
        m1.setDetails(details);
        m1.setUser(user);

        dto1 = convertToDTO(m1);
    }

    @AfterEach
    void close() throws Exception{
        closeable.close();
    }

    private ManagerDTO convertToDTO(Manager m){
        ManagerDTO dto = new ManagerDTO();
        dto.setId(m.getId());
        dto.setUser(convertUser(m.getUser()));
        dto.setDetails(convertDetails(m.getDetails()));

        return dto;
    }

    private AppUserDTO convertUser(AppUser u){
        AppUserDTO dto = new AppUserDTO();
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setEmail(u.getEmail());
        dto.setPassword(u.getPassword());

        return dto;
    }

    private PersonalDetailsDTO convertDetails(PersonalDetails d){
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

    @Test
    public void test01WhenGivenARequestToRetrieveManagerByUserIdManagerIsReturned(){
        when(repo.findManagerByUserId(103)).thenReturn(m1);

        //Convertor Mock Behaviour
        when(convertor.convert(m1)).thenReturn(dto1);

        ManagerDTO expectedManager = dto1;

        ManagerDTO newCat = service.getManagerByAppUserId(103L);

        //Assert Cats are the same
        assertEquals(newCat, expectedManager);
    }

    @Test
    public void test02WhenGivenARequestToRetrieveManagerByIdManagerIsReturned(){
        when(repo.findById(34L)).thenReturn(java.util.Optional.of(m1));

        //Convertor Mock Behaviour
        when(convertor.convert(m1)).thenReturn(dto1);

        ManagerDTO expectedManager = dto1;

        ManagerDTO newCat = service.findByManagerId(34L);

        //Assert Cats are the same
        assertEquals(newCat, expectedManager);
    }
}
