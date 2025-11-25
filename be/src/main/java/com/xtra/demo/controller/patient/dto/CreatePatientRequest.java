package com.xtra.demo.controller.patient.dto;


import com.xtra.demo.data.entity.Gender;
import com.xtra.demo.errors.validation.ValidDob;
import com.xtra.demo.errors.validation.ValueOfEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePatientRequest {

    @NotEmpty(message = "Value firstName can't be empty.")
    @Size(message = "Size of firstName must be between 1 and 100 character length.", min = 1, max = 100)
    private String firstName;

    @NotEmpty(message = "Value lastName can't be empty.")
    @Size(message = "Size of lastName must be between 1 and 100 character length.", min = 1, max = 100)
    private String lastName;

    @NotEmpty(message = "Value dateOfBirth can't be empty.")
    @ValidDob(message = "Value of dateOfBirth must be in 'YYYY-MM-DD' format.")
    private String dateOfBirth;

    @NotEmpty(message = "Value gender can't be empty.")
    @ValueOfEnum(enumClass = Gender.class, message = "Value of gender is either 'M' or 'F'.")
    private String gender;

    @NotEmpty(message = "Value phone can't be empty.")
    @Size(message = "Size of phone must be 15 character length.", min = 15, max = 15)
    private String phone;

    @NotEmpty(message = "Value address can't be empty.")
    @Size(message = "Size of address must be between 10 and 100 character length.", min = 10, max = 100)
    private String address;

    @NotEmpty(message = "Value suburb can't be empty.")
    @Size(message = "Size of suburb must be between 10 and 100 character length.", min = 10, max = 100)
    private String suburb;

    @NotEmpty(message = "Value state can't be empty.")
    @Size(message = "Size of state must be between 10 and 100 character length.", min = 5, max = 100)
    private String state;

    @NotEmpty(message = "Value postcode can't be empty.")
    @Size(message = "Size of postcode must be 4 character length.", min = 4, max = 4)
    private String postcode;

}
