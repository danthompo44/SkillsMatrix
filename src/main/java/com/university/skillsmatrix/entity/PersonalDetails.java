package com.university.skillsmatrix.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Personal_Details")
@Table(name = "personal_details")
@Getter
@Setter
public class PersonalDetails {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name= "personal_details_sequence", sequenceName = "personal_details_sequence_id", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="personal_details_sequence")
    private long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "address_first_line")
    private String addressFirstLine;

    @NotNull
    @Column(name = "address_second_line")
    private String addressSecondLine;

    @NotNull
    @Column(name = "county")
    private String county;

    @NotNull
    @Column(name = "postcode")
    private String postcode;

    public String toString(){
        return String.format("%s %s (%d)\n" +
                "%s\n%s\n%s\n%s", firstName, surname, id,
                addressFirstLine, addressSecondLine,
                county, postcode);
    }
}
