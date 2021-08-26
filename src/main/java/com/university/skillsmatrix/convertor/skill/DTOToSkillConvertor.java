package com.university.skillsmatrix.convertor.skill;

import com.university.skillsmatrix.convertor.category.DTOToSkillCategoryConvertor;
import com.university.skillsmatrix.convertor.staff.DTOToStaffConvertor;
import com.university.skillsmatrix.domain.*;
import com.university.skillsmatrix.entity.*;

import java.util.ArrayList;
import java.util.List;

public class DTOToSkillConvertor {
    private final DTOToSkillCategoryConvertor catConvertor = new DTOToSkillCategoryConvertor();
    private final DTOToStaffConvertor staffConvertor = new DTOToStaffConvertor();

    public Skill convert(SkillDTO dto){
        Skill skill = new Skill();
        skill.setId(dto.getId());
        skill.setName(dto.getName());
        skill.setCategory(catConvertor.convert(dto.getCategory()));
        skill.setStaffList(addStaff(dto.getStaffList()));

        return skill;
    }

    private List<Staff> addStaff(List<StaffDTO> staffDTOList){
        List<Staff> staffList = new ArrayList<>();
        for(StaffDTO d: staffDTOList){
            Staff s = staffConvertor.convert(d);
            staffList.add(s);
        }
        return staffList;
    }
}
