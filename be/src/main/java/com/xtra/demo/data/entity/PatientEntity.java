package com.xtra.demo.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.util.Date;
import java.util.UUID;

@Table(name = "patients")
@Entity
@Getter
@Setter
public class PatientEntity {

    @Id
    private UUID pid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write = "?::gender")
    private Gender gender;
    private String phone;
    private String address;
    private String suburb;
    private String state;
    private String postcode;

}
