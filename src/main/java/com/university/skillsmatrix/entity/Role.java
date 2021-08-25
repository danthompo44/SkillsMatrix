package com.university.skillsmatrix.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Role")
@Table(name = "role")
@Getter
@Setter
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "type")
    private String type;

    public String toString(){
        return String.format("%d, %s, %s" , id, type);
    }
 }
