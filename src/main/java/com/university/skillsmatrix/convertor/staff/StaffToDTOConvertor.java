package com.university.skillsmatrix.convertor.staff;

import com.university.skillsmatrix.convertor.category.SkillCategoryToDTOConvertor;
import com.university.skillsmatrix.convertor.details.PersonalDetailsToDTOConvertor;
import com.university.skillsmatrix.convertor.manager.ManagerToDTOConvertor;
import com.university.skillsmatrix.convertor.user.AppUserToDTOConvertor;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.domain.StaffDTO;
import com.university.skillsmatrix.entity.Skill;
import com.university.skillsmatrix.entity.Staff;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class StaffToDTOConvertor {
    PersonalDetailsToDTOConvertor detailsConvertor = new PersonalDetailsToDTOConvertor();
    ManagerToDTOConvertor managerConvertor = new ManagerToDTOConvertor();
    AppUserToDTOConvertor userConvertor = new AppUserToDTOConvertor();
    SkillCategoryToDTOConvertor catConvertor = new SkillCategoryToDTOConvertor();

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
            dtoList.add(convertSkill(s));
        }

        return dtoList;
    }

    private SkillDTO convertSkill(Skill s){
        SkillDTO dto = new SkillDTO();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setCategory(catConvertor.convert(s.getCategory()));
        return dto;
    }
}
