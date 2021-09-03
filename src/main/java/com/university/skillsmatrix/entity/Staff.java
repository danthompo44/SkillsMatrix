package com.university.skillsmatrix.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Staff")
@Table(name = "staff")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Staff {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name= "staff_sequence", sequenceName = "staff_sequence_id", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="staff_sequence")
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "details_id")
    private PersonalDetails details;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="staff_skill",
            joinColumns= @JoinColumn( name="staff_id"),
            inverseJoinColumns=@JoinColumn(name="skill_id")
    )
    private List<Skill> skills = new ArrayList<>();
}
