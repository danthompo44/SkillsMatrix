package com.university.skillsmatrix.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name="App_user")
@Table(name = "app_user")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="app_user_role",
            joinColumns= @JoinColumn( name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private List<Role> roles = new ArrayList<>();

    public String toString(){
        return String.format("%d, %s, %s, %s",
                id, username, password, email);
    }
}
