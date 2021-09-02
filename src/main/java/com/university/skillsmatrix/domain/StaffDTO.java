package com.university.skillsmatrix.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StaffDTO {
    private long id;

    @NotNull(message = "Personal details cannot be blank")
    private PersonalDetailsDTO details;

    @NotNull(message = "User cannot be blank")
    private AppUserDTO user;

    @NotNull(message = "Manager cannot be blank")
    private ManagerDTO manager;

    private List<SkillDTO> skills = new ArrayList<>();

    public void addSkill(SkillDTO skill){
        if(skills.contains(skill)) return;
        skills.add(skill);
    }

    public void removeSkill(SkillDTO skill){
        skills.remove(skill);
    }

    private List<StaffSkillDTO> staffSkillList;

    public void addStaffSkill(StaffSkillDTO staffSkillDTO){
        if(staffSkillList.contains(staffSkillDTO)) return;
        staffSkillList.add(staffSkillDTO);
    }

    public void removeStaffSkill(StaffSkillDTO staffSkillDTO){
        staffSkillList.remove(staffSkillDTO);
    }
}
