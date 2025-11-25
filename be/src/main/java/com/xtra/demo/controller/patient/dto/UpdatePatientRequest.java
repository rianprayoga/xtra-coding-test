package com.xtra.demo.controller.patient.dto;
;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class UpdatePatientRequest extends CreatePatientRequest{

    public UpdatePatientRequest(String firstName, String lastName, String dateOfBirth, String gender, String phone, String address, String suburb, String state, String postcode) {
        super(firstName, lastName, dateOfBirth, gender, phone, address, suburb, state, postcode);
    }

    @NotNull(message = "Value of version can't be empty.")
    private int version;
}
