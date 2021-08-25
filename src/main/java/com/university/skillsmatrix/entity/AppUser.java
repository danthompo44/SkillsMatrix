package com.university.skillsmatrix.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Staff")
@Table(name = "staff")
@Getter
@Setter
public class AppUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Email(message="{errors.invalid_email}")
    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "details_id")
    private PersonalDetails details;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="user_skill",
            joinColumns= @JoinColumn( name="user_id"),
            inverseJoinColumns=@JoinColumn(name="skill_id")
    )
    private List<Skill> skills = new ArrayList<>();

    public String toString(){
        return String.format("%d, %s, %s, %s", id, username, password, email);
    }
}
