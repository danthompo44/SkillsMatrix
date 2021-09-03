package com.university.skillsmatrix.convertor.staffSkill;

import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.domain.StaffSkillIdDTO;
import org.springframework.stereotype.Component;

@Component
public class StaffSkillDTOToStaffSkillIdDTO {
    public StaffSkillIdDTO convert(StaffSkillDTO staffSkillDTO){
        StaffSkillIdDTO idDto = new StaffSkillIdDTO();

        idDto.setId(staffSkillDTO.getId());
        idDto.setExpiryDate(staffSkillDTO.getExpiryDate());
        idDto.setSkillStrength(staffSkillDTO.getSkillStrength());
        idDto.setStaffId(staffSkillDTO.getStaff().getId());
        idDto.setSkillId(staffSkillDTO.getSkill().getId());

        return idDto;
    }
}
