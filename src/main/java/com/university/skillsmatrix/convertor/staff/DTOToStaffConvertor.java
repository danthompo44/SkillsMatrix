package com.university.skillsmatrix.convertor.staff;

import com.university.skillsmatrix.convertor.skill.DTOToSkillConvertor;
import com.university.skillsmatrix.convertor.details.DTOToPersonalDetailsConvertor;
import com.university.skillsmatrix.convertor.manager.DTOToManagerConvertor;
import com.university.skillsmatrix.convertor.user.DTOToAppUserConvertor;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.domain.StaffDTO;
import com.university.skillsmatrix.entity.Skill;
import com.university.skillsmatrix.entity.Staff;

import java.util.ArrayList;
import java.util.List;

public class DTOToStaffConvertor {
    private final DTOToPersonalDetailsConvertor detailsConvertor = new DTOToPersonalDetailsConvertor();
    private final DTOToManagerConvertor managerConvertor = new DTOToManagerConvertor();
    private final DTOToSkillConvertor skillConvertor = new DTOToSkillConvertor();
    private final DTOToAppUserConvertor userConvertor = new DTOToAppUserConvertor();

    public Staff convert(StaffDTO dto){
        Staff s = new Staff();
        s.setId(dto.getId());
        s.setDetails(detailsConvertor.convert(dto.getDetails()));
        s.setManager(managerConvertor.convert(dto.getManager()));
        s.setUser(userConvertor.convert(dto.getUser()));
        s.setSkills(addSkills(dto.getSkills()));

        return s;
    }

    private List<Skill> addSkills(List<SkillDTO> dtoList){
        List<Skill> skillList = new ArrayList<>();

        for(SkillDTO s: dtoList){
            skillList.add(skillConvertor.convert(s));
        }

        return skillList;
    }
}
