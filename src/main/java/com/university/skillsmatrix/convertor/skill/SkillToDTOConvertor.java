package com.university.skillsmatrix.convertor.skill;

import com.university.skillsmatrix.convertor.category.SkillCategoryToDTOConvertor;
import com.university.skillsmatrix.convertor.staff.StaffToDTOConvertor;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.domain.StaffDTO;
import com.university.skillsmatrix.entity.Skill;
import com.university.skillsmatrix.entity.Staff;

import java.util.ArrayList;
import java.util.List;

public class SkillToDTOConvertor {
    private final SkillCategoryToDTOConvertor categoryToDTOConvertor = new SkillCategoryToDTOConvertor();
    private final StaffToDTOConvertor staffConvertor = new StaffToDTOConvertor();

    public SkillDTO convert(Skill skill){
        SkillDTO dto = new SkillDTO();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        dto.setCategory(categoryToDTOConvertor.convert(skill.getCategory()));
        dto.setStaffList(addStaff(skill.getStaffList()));

        return dto;
    }

    private List<StaffDTO> addStaff(List<Staff> staffList){
        List<StaffDTO> dtoList = new ArrayList<>();
        for(Staff s: staffList){
            dtoList.add(staffConvertor.convert(s));
        }
        return dtoList;
    }
}
