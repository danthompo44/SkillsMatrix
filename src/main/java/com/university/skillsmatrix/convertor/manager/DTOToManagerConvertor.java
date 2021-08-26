package com.university.skillsmatrix.convertor.manager;

import com.university.skillsmatrix.convertor.details.DTOToPersonalDetailsConvertor;
import com.university.skillsmatrix.convertor.user.DTOToAppUserConvertor;
import com.university.skillsmatrix.domain.ManagerDTO;
import com.university.skillsmatrix.entity.Manager;
import org.springframework.stereotype.Component;

@Component
public class DTOToManagerConvertor {
    DTOToPersonalDetailsConvertor detailsConvertor = new DTOToPersonalDetailsConvertor();
    DTOToAppUserConvertor userConvertor = new DTOToAppUserConvertor();

    public Manager convert(ManagerDTO dto){
        Manager m = new Manager();
        m.setId(dto.getId());
        m.setDetails(detailsConvertor.convert(dto.getDetails()));
        m.setUser(userConvertor.convert(dto.getUser()));

        return m;
    }
}
