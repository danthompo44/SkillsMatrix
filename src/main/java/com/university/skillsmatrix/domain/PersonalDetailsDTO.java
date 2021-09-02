package com.university.skillsmatrix.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
public class PersonalDetailsDTO {
    private long id;

    @NotNull(message = "First name cannot be null")
    @Size(min = 1, max = 50, message = "First name cannot be bigger than 50 characters")
    private String firstName;

    @NotNull(message = "Surname cannot be null")
    @Size(min = 1, max = 50, message = "Surname cannot be bigger than 50 characters")
    private String surname;

    @NotNull(message = "Address first line cannot be null")
    @Size(min = 1, max = 50, message = "Address first line cannot be bigger than 50 characters")
    private String addressFirstLine;

    @NotNull(message = "Address second line cannot be null")
    @Size(min = 1, max = 50, message = "Address second line cannot be bigger than 50 characters")
    private String addressSecondLine;

    @NotNull(message = "County cannot be null")
    @Size(min = 1, max = 50, message = "County cannot be bigger than 50 characters")
    private String county;

    @NotNull(message = "Postcode cannot be null")
    @Size(min = 1, max = 50, message = "Postcode cannot be bigger than 50 characters")
    private String postcode;

    public String toString(){
        return String.format("%s %s (%d)\n" +
                        "%s\n%s\n%s\n%s", firstName, surname, id,
                addressFirstLine, addressSecondLine,
                county, postcode);
    }

    public String getFullName(){
        return String.format("%s %s", firstName, surname);
    }
}
