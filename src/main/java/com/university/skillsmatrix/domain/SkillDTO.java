package com.university.skillsmatrix.domain;

import com.university.skillsmatrix.entity.Staff;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SkillDTO {
    private long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min=1, max = 50, message="Name no more than 50 characters")
    private String name;

    @NotBlank(message = "Skill category cannot be blank")
    private SkillCategoryDTO category;

    private List<StaffDTO> staffList = new ArrayList<>();

    public void addStaff(StaffDTO staff){
        if(staffList.contains(staff)) return;
        staffList.add(staff);
    }

    public void removeUser(StaffDTO staff){
        staffList.remove(staff);
    }
}
