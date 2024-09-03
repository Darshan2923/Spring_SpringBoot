package com.freecodecampspring.freecodecampspring.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "FirstName should not be empty") String firstName,
        @NotEmpty(message = "LastName should not be empty") String lastName,
        @Email String email,
        Integer school_id) {

}
