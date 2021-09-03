package com.university.skillsmatrix.entity;

import com.university.skillsmatrix.helper.DateHelper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

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

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

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
