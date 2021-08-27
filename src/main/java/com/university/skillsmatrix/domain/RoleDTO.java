package com.university.skillsmatrix.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RoleDTO {
    private long id;

    @NotNull(message = "Role type cannot be null")
    @Size(min=1, max=15, message="Role type must be no more than 15 characters")
    private String type;
}
