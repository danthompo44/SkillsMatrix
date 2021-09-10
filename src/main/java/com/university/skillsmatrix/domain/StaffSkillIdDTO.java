package com.university.skillsmatrix.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StaffSkillIdDTO {
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;

    @Min(value = 0, message =
            "Skill strength must be positive")
    @Max(value = 10, message =
            "Skill strength has a maximum of 10")
    private int skillStrength;

    @NotNull(message = "Skill cannot be null")
    private Long skillId;

    @NotNull(message = "Staff cannot be null")
    private Long staffId;
}
