package com.university.skillsmatrix.convertor.staffSkill;

import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.entity.StaffSkill;

import java.util.ArrayList;
import java.util.List;

public class StaffSkillToDTOConvertor {
    public List<StaffSkillDTO> convert(List<StaffSkill> staffSkillList){
        List<StaffSkillDTO> dtoList = new ArrayList<>();

        for(StaffSkill s: staffSkillList){
            StaffSkillDTO dto = new StaffSkillDTO();
            dto.setId(s.getId());
            dto.setExpiryDate(s.getExpiryDate());
            dto.setSkillStrength(s.getSkillStrength());

            dtoList.add(dto);
        }
        return dtoList;
    }
}
