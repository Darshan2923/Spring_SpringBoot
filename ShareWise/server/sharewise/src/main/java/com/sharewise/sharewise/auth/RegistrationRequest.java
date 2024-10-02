package com.sharewise.sharewise.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "FirstName shouldn't be empty")
    @NotBlank(message = "FirstName shouldn't be empty")
    private String firstname;
    @NotEmpty(message = "LastName shouldn't be empty")
    @NotBlank(message = "LastName shouldn't be empty")
    private String lastname;
    @NotEmpty(message = "Email shouldn't be empty")
    @NotBlank(message = "Email shouldn't be empty")
    @Email(message = "Email not valid")
    private String email;
    @NotEmpty(message = "Password shouldn't be empty")
    @NotBlank(message = "Password shouldn't be empty")
    @Size(min = 8, message = "Atleast 8 characters needed for a password")
    private String password;
}