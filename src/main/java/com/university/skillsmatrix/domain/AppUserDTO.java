package com.university.skillsmatrix.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
public class AppUserDTO {
    private long id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 1, max = 50, message = "Username cannot exceed 50 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 1, max = 50, message = "Password cannot exceed 50 characters")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email(message="{errors.invalid_email}")
    private String email;

    @NotNull(message = "Role cannot be null")
    private RoleDTO role;

    public String toString(){
        return String.format("%d, %s, %s, %s", id, username, password, email);
    }
}
