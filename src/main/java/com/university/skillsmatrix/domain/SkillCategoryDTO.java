package com.university.skillsmatrix.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SkillCategoryDTO {
    private long id;

    @NotEmpty(message = "Category description cannot be null")
    @Size(min=1, max = 50, message = "Description no more than 50 characters")
    private String description;
}
