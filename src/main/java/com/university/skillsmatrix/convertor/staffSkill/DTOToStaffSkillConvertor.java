package com.university.skillsmatrix.convertor.staffSkill;

import com.university.skillsmatrix.convertor.skill.DTOToSkillConvertor;
import com.university.skillsmatrix.convertor.staff.DTOToStaffConvertor;
import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.entity.StaffSkill;
import org.springframework.stereotype.Component;

@Component
public class DTOToStaffSkillConvertor {
    private final DTOToSkillConvertor skillConvertor = new DTOToSkillConvertor();
    private final DTOToStaffConvertor staffConvertor = new DTOToStaffConvertor();
    public StaffSkill convert(StaffSkillDTO dto){
        StaffSkill staffSkill = new StaffSkill();

        staffSkill.setId(dto.getId());
        staffSkill.setCreatedAt(dto.getCreatedAt());
        staffSkill.setExpiryDate(dto.getExpiryDate());
        staffSkill.setSkillStrength(dto.getSkillStrength());
        staffSkill.setSkill(skillConvertor.convert(dto.getSkill()));
        staffSkill.setStaff(staffConvertor.convert(dto.getStaff()));

        return staffSkill;
    }
}
