package com.university.skillsmatrix.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;

@Entity(name = "StaffSkill")
@Table(name = "staff_skill")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StaffSkill {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name= "staff_skill_sequence",
            sequenceName = "staff_skill_sequence_id", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO,
            generator="staff_skill_sequence")
    private long id;

    @Column(name="expiry_date")
    private Date expiryDate;

    @Column(name="skill_strength")
    private int skillStrength;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "skill_id")
    private Skill skill;
}
