package com.university.skillsmatrix.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, String details){
        this.message = message;
        this.details = details;
    }

    private String message;

    private String details;
}

