package com.university.skillsmatrix.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ManagerDTO {
    private long id;

    @NotNull
    private PersonalDetailsDTO details;

    @NotNull
    private AppUserDTO user;
}
