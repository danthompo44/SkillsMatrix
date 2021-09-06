package com.university.skillsmatrix.convertor.staffSkill;

import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.domain.StaffDTO;
import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.domain.StaffSkillIdDTO;
import org.springframework.stereotype.Component;

@Component
public class IdDTOToStaffSkillDTOConvertor {
    public StaffSkillDTO convert(StaffSkillIdDTO idDTO, StaffDTO staffDTO, SkillDTO skillDTO){
        StaffSkillDTO staffSkillDTO = new StaffSkillDTO();
        staffSkillDTO.setId(idDTO.getId());
        staffSkillDTO.setExpiryDate(idDTO.getExpiryDate());
        staffSkillDTO.setSkillStrength(idDTO.getSkillStrength());
        staffSkillDTO.setStaff(staffDTO);
        staffSkillDTO.setSkill(skillDTO);

        return staffSkillDTO;
    }
}
