package com.university.skillsmatrix.convertor.details;

import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import com.university.skillsmatrix.entity.PersonalDetails;

public class PersonalDetailsToDTOConvertor {
    public PersonalDetailsDTO convert(PersonalDetails d){
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
}
