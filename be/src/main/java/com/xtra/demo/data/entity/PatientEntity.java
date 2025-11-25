package com.xtra.demo.data.entity;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import java.sql.Date;
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
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "updated_at")
    private Long updatedAt;
    private int version;
    private boolean active;

}
