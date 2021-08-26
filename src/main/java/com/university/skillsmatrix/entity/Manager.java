package com.university.skillsmatrix.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity(name = "Manager")
@Table(name = "manager")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Manager{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name= "staff_sequence", sequenceName = "staff_sequence_id", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="staff_sequence")
    private long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "details_id")
    private PersonalDetails details;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private AppUser user;
}
