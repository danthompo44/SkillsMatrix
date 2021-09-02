package com.university.skillsmatrix.convertor.staffSkill;

import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.entity.StaffSkill;
import java.util.ArrayList;
import java.util.List;

public class DTOToStaffSkillConvertor {
    public List<StaffSkill> convert(List<StaffSkillDTO> staffSkillDTOS){
        List<StaffSkill> staffSkillList = new ArrayList<>();

        for (StaffSkillDTO dto: staffSkillDTOS){
            StaffSkill staffSkill = new StaffSkill();
            staffSkill.setId(dto.getId());
            staffSkill.setExpiryDate(dto.getExpiryDate());
            staffSkill.setSkillStrength(dto.getSkillStrength());

            staffSkillList.add(staffSkill);
        }

        return staffSkillList;
    }
}
