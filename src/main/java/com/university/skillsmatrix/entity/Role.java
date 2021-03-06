package com.university.skillsmatrix.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Role")
@Table(name = "role")
@Getter
@Setter
@ToString
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "type")
    private String type;

    @ManyToMany(mappedBy = "roles")
    private List<AppUser> users = new ArrayList<>();
 }
