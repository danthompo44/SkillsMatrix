package com.university.skillsmatrix.service;

import com.university.skillsmatrix.convertor.details.DTOToPersonalDetailsConvertor;
import com.university.skillsmatrix.convertor.details.PersonalDetailsToDTOConvertor;
import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import com.university.skillsmatrix.entity.PersonalDetails;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.repository.PersonalDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DetailsService {
    private final PersonalDetailsRepository repository;
    private final PersonalDetailsToDTOConvertor detailsToDTOConvertor;
    private final DTOToPersonalDetailsConvertor dtoToDetailsConvertor;

    public PersonalDetailsDTO getDetailsById(Long id){
        Optional<PersonalDetails> details = repository.findById(id);

        if(details.isPresent()){
            return detailsToDTOConvertor.convert(details.get());
        }
        else{
            throw new ResourceNotFoundException("Staff member is not found", "Enter a valid id");
        }
    }

    public PersonalDetailsDTO save(PersonalDetailsDTO dto){
        PersonalDetails details = dtoToDetailsConvertor.convert(dto);
        repository.save(details);
        return detailsToDTOConvertor.convert(details);
    }

    public void removeDetails(Long id){
        Optional<PersonalDetails> d = repository.findById(id);
        if(d.isPresent()){
            PersonalDetails details = d.get();
            details.setFirstName("");
            details.setSurname("");
            details.setAddressFirstLine("");
            details.setAddressSecondLine("");
            details.setCounty("");
            details.setPostcode("");
        }
    }
}
