package com.university.skillsmatrix.convertor.staff;

import com.university.skillsmatrix.convertor.skill.SkillToDTOConvertor;
import com.university.skillsmatrix.convertor.details.PersonalDetailsToDTOConvertor;
import com.university.skillsmatrix.convertor.manager.ManagerToDTOConvertor;
import com.university.skillsmatrix.convertor.user.AppUserToDTOConvertor;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.domain.StaffDTO;
import com.university.skillsmatrix.entity.Skill;
import com.university.skillsmatrix.entity.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffToDTOConvertor {
    PersonalDetailsToDTOConvertor detailsConvertor = new PersonalDetailsToDTOConvertor();
    ManagerToDTOConvertor managerConvertor = new ManagerToDTOConvertor();
    AppUserToDTOConvertor userConvertor = new AppUserToDTOConvertor();
    SkillToDTOConvertor skillConvertor = new SkillToDTOConvertor();

    public StaffDTO convert(Staff s){
        StaffDTO dto = new StaffDTO();
        dto.setId(s.getId());
        dto.setDetails(detailsConvertor.convert(s.getDetails()));
        dto.setManager(managerConvertor.convert(s.getManager()));
        dto.setUser(userConvertor.convert(s.getUser()));
        dto.setSkills(addSkills(s.getSkills()));

        return dto;
    }

    private List<SkillDTO> addSkills(List<Skill> skillList){
        List<SkillDTO> dtoList = new ArrayList<>();

        for(Skill s: skillList){
            dtoList.add(skillConvertor.convert(s));
        }

        return dtoList;
    }
}
