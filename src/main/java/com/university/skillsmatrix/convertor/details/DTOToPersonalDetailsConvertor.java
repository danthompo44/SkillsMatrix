package com.university.skillsmatrix.convertor.details;

import com.university.skillsmatrix.domain.PersonalDetailsDTO;
import com.university.skillsmatrix.entity.PersonalDetails;
import org.springframework.stereotype.Component;

@Component
public class DTOToPersonalDetailsConvertor {
    public PersonalDetails convert(PersonalDetailsDTO dto){
        PersonalDetails d = new PersonalDetails();
        d.setId(d.getId());
        d.setFirstName(dto.getFirstName());
        d.setSurname(dto.getSurname());
        d.setAddressFirstLine(dto.getAddressFirstLine());
        d.setAddressSecondLine(dto.getAddressSecondLine());
        d.setCounty(dto.getCounty());
        d.setPostcode(dto.getPostcode());

        return d;
    }
}
