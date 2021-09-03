package com.university.skillsmatrix.domain;

import com.university.skillsmatrix.helper.DateHelper;
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

    private Date createdAt;

    private Date expiryDate;

    @Min(value = 0, message = "Skill strength must be positive")
    @Max(value = 10, message = "Skill strength has a maximum of 10")
    private int skillStrength;

    @NotNull(message = "Skill cannot be null")
    private SkillDTO skill;

    @NotNull(message = "Staff cannot be null")
    private StaffDTO staff;

     public void setDates(){
         createdAt = DateHelper.getCurrentDate();
         expiryDate = DateHelper.getAYearFromToday();
     }
}
