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

    @NotNull
    private PersonalDetailsDTO details;

    @NotNull
    private AppUserDTO user;

    @NotNull
    private ManagerDTO manager;

    private List<SkillDTO> skills = new ArrayList<>();

    public void addSkill(SkillDTO skill){
        if(skills.contains(skill)) return;
        skills.add(skill);
    }

    public void removeSkill(SkillDTO skill){
        skills.remove(skill);
    }
}
