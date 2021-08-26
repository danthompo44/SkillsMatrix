package com.university.skillsmatrix.convertor.manager;

import com.university.skillsmatrix.convertor.details.PersonalDetailsToDTOConvertor;
import com.university.skillsmatrix.convertor.user.AppUserToDTOConvertor;
import com.university.skillsmatrix.domain.ManagerDTO;
import com.university.skillsmatrix.entity.Manager;

public class ManagerToDTOConvertor {
    private final AppUserToDTOConvertor userConverter = new AppUserToDTOConvertor();
    private final PersonalDetailsToDTOConvertor detailsConvertor = new PersonalDetailsToDTOConvertor();

    public ManagerDTO convert(Manager m){
        ManagerDTO dto = new ManagerDTO();
        dto.setId(m.getId());
        dto.setUser(userConverter.convert(m.getUser()));
        dto.setDetails(detailsConvertor.convert(m.getDetails()));

        return dto;
    }
}
