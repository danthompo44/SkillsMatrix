package com.university.skillsmatrix.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StaffSkillDTO {
    private long id;

    @NotNull(message = "The expiry date cannot be null")
    private Date expiryDate;

    @Min(value = 0, message = "Skill strength must be positive")
    @Max(value = 10, message = "Skill strength has a maximum of 10")
    private int skillStrength;
}
