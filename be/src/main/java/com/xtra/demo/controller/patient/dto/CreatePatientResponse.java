package com.xtra.demo.controller.patient.dto;

import com.xtra.demo.data.entity.PatientEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CreatePatientResponse {
    private String pid;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String phone;
    private String address;
    private String suburb;
    private String state;
    private String postcode;
    private int version;
    private String createdAt;
    private String updatedAt;

    public static CreatePatientResponse from(PatientEntity entity){

        return CreatePatientResponse.builder()
                .pid(entity.getPid().toString())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .dateOfBirth(entity.getDateOfBirth().toString())
                .gender(entity.getGender().toString())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .suburb(entity.getSuburb())
                .state(entity.getState())
                .postcode(entity.getPostcode())
                .version(entity.getVersion())
                .createdAt(Instant.ofEpochMilli(entity.getCreatedAt()).toString())
                .updatedAt(Instant.ofEpochMilli(entity.getUpdatedAt()).toString())
                .build();
    }

}
