package com.university.skillsmatrix.domain;

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

    private List<AppUserDTO> users = new ArrayList<>();

    public void addUser(AppUserDTO user){
        if(users.contains(user)) return;
        users.add(user);
    }

    public void removeUser(AppUserDTO user){
        users.remove(user);
    }
}
