package com.university.skillsmatrix.convertor.role;

import com.university.skillsmatrix.domain.RoleDTO;
import com.university.skillsmatrix.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class DTOToRoleConvertor {
    public Role convert(RoleDTO dto){
        Role r = new Role();
        r.setId(dto.getId());
        r.setType(dto.getType());

        return r;
    }
}
