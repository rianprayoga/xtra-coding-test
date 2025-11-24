package com.xtra.demo.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "patients")
@Entity
@Setter
@Getter
public class PatientEntity {

    @Id
    private String pid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
    private String suburb;
    private String state;
    private String postcode;

}
