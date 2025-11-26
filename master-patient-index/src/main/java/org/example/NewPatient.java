package org.example;

public record NewPatient(
        String firstName,
        String lastName,
        Gender gender,
        String nationalId,
        String email,
        String phone,
        String dob)
{
}
