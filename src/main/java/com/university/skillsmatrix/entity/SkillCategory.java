package com.university.skillsmatrix.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "skill_category")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SkillCategory {
    @Id
    @Column(name="id")
    @SequenceGenerator(name= "skill_category_sequence",
            sequenceName = "skill_category_sequence_id", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO,
            generator="skill_category_sequence")
    private long id;

    @Column(name="description")
    @NotBlank
    private String description;
}
