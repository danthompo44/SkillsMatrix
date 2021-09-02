package com.university.skillsmatrix.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.sql.Date;

@Entity(name = "StaffSkill")
@Table(name = "staff_skill")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StaffSkill {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name= "staff_sequence", sequenceName = "staff_sequence_id", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="staff_sequence")
    private long id;

    @Column(name="expiry_date")
    private Date expiryDate;

    @Column(name="skill_strength")
    private int skillStrength;
}
