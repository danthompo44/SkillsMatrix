package com.university.skillsmatrix.convertor.user;

import com.university.skillsmatrix.convertor.role.RoleToDTOConvertor;
import com.university.skillsmatrix.domain.AppUserDTO;
import com.university.skillsmatrix.entity.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserToDTOConvertor {
    private final RoleToDTOConvertor roleConvertor = new RoleToDTOConvertor();

    public AppUserDTO convert(AppUser u){
        AppUserDTO dto = new AppUserDTO();
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setEmail(u.getEmail());
        dto.setPassword(u.getPassword());
//        dto.setRole(roleConvertor.convert(u.getRole()));

        return dto;
    }
}
