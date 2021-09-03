package com.university.skillsmatrix.convertor.staffSkill;

import com.university.skillsmatrix.convertor.skill.SkillToDTOConvertor;
import com.university.skillsmatrix.convertor.staff.StaffToDTOConvertor;
import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.entity.StaffSkill;
import org.springframework.stereotype.Component;

@Component
public class StaffSkillToDTOConvertor {
    private final StaffToDTOConvertor staffConvertor = new StaffToDTOConvertor();
    private final SkillToDTOConvertor skillConvertor = new SkillToDTOConvertor();

    public StaffSkillDTO convert(StaffSkill staffSkill){
        StaffSkillDTO dto = new StaffSkillDTO();

        dto.setId(staffSkill.getId());
        dto.setExpiryDate(staffSkill.getExpiryDate());
        dto.setSkillStrength(staffSkill.getSkillStrength());
        dto.setSkill(skillConvertor.convert(staffSkill.getSkill()));
        dto.setStaff(staffConvertor.convert(staffSkill.getStaff()));

        return dto;

    }
}
