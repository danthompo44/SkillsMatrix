package com.university.skillsmatrix.convertor.role;

import com.university.skillsmatrix.domain.RoleDTO;
import com.university.skillsmatrix.entity.Role;

public class RoleToDTOConvertor {
    public RoleDTO convert(Role r){
        RoleDTO dto = new RoleDTO();

        dto.setId(r.getId());
        dto.setType(r.getType());

        return dto;
    }
}
