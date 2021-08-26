package com.university.skillsmatrix.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skill")
@Getter
@Setter
public class Skill {
    @Id
    @Column(name="id")
    @SequenceGenerator(name= "skill_sequence", sequenceName = "skill_sequence_id", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="skill_sequence")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="category_Id")
    private SkillCategory category;

    @ManyToMany(mappedBy = "skills")
    private List<Staff> staffList = new ArrayList<>();

    public String toString(){
        return String.format("%d, %s, %s, %s" ,
                id, name, category, staffList);
    }
}
