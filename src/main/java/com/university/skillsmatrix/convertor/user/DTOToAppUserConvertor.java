package com.university.skillsmatrix.convertor.user;

import com.university.skillsmatrix.convertor.role.DTOToRoleConvertor;
import com.university.skillsmatrix.domain.AppUserDTO;
import com.university.skillsmatrix.entity.AppUser;
import org.springframework.stereotype.Component;

@Component
public class DTOToAppUserConvertor {
    private final DTOToRoleConvertor roleConvertor = new DTOToRoleConvertor();

    public AppUser convert(AppUserDTO dto){
        AppUser u = new AppUser();
        u.setId(dto.getId());
        u.setUsername(dto.getUsername());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        u.setRole(roleConvertor.convert(dto.getRole()));

        return u;
    }
}
