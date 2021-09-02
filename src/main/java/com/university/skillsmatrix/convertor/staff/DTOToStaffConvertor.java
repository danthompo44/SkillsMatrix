package com.university.skillsmatrix.convertor.staff;

import com.university.skillsmatrix.convertor.category.DTOToSkillCategoryConvertor;
import com.university.skillsmatrix.convertor.details.DTOToPersonalDetailsConvertor;
import com.university.skillsmatrix.convertor.manager.DTOToManagerConvertor;
import com.university.skillsmatrix.convertor.staffSkill.DTOToStaffSkillConvertor;
import com.university.skillsmatrix.convertor.user.DTOToAppUserConvertor;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.domain.StaffDTO;
import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.entity.Skill;
import com.university.skillsmatrix.entity.Staff;
import com.university.skillsmatrix.entity.StaffSkill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOToStaffConvertor {
    private final DTOToPersonalDetailsConvertor detailsConvertor = new DTOToPersonalDetailsConvertor();
    private final DTOToManagerConvertor managerConvertor = new DTOToManagerConvertor();
    private final DTOToAppUserConvertor userConvertor = new DTOToAppUserConvertor();
    private final DTOToSkillCategoryConvertor catConvertor = new DTOToSkillCategoryConvertor();
    private final DTOToStaffSkillConvertor staffSkillConvertor = new DTOToStaffSkillConvertor();

    public Staff convert(StaffDTO dto){
        Staff s = new Staff();
        s.setId(dto.getId());
        s.setDetails(detailsConvertor.convert(dto.getDetails()));
        s.setManager(managerConvertor.convert(dto.getManager()));
        s.setUser(userConvertor.convert(dto.getUser()));
        s.setSkills(addSkills(dto.getSkills()));
        s.setStaffSkills(staffSkillConvertor.convert(dto.getStaffSkillList()));

        return s;
    }

    private List<Skill> addSkills(List<SkillDTO> dtoList){
        List<Skill> skillList = new ArrayList<>();

        for(SkillDTO s: dtoList){
            skillList.add(convertSkill(s));
        }

        return skillList;
    }

    private Skill convertSkill(SkillDTO dto){
        Skill skill = new Skill();
        skill.setId(dto.getId());
        skill.setName(dto.getName());
        skill.setCategory(catConvertor.convert(dto.getCategory()));

        return skill;
    }

    private List<Staff> addStaff(List<StaffDTO> staffDTOList){
        List<Staff> staffList = new ArrayList<>();

        for(StaffDTO dto: staffDTOList){
            Staff s = new Staff();
            s.setId(dto.getId());
            s.setUser(userConvertor.convert(dto.getUser()));
            s.setDetails(detailsConvertor.convert(dto.getDetails()));
            s.setManager(managerConvertor.convert(dto.getManager()));
            s.setSkills(addSkills(dto.getSkills()));

            staffList.add(s);
        }

        return staffList;
    }
}
