package com.xtra.demo.service;

import com.xtra.demo.controller.patient.dto.CreatePatientRequest;
import com.xtra.demo.data.entity.Gender;
import com.xtra.demo.data.entity.PatientEntity;
import com.xtra.demo.data.repository.PatientRepository;
import com.xtra.demo.errors.http.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void addPatient(CreatePatientRequest request){

        if (patientRepository.phoneNumberExist(request.getPhone())){
            throw new ConflictException("Phone number %s already registered.".formatted(request.getPhone()));
        }

        PatientEntity entity = new PatientEntity();
        entity.setPid(UUID.randomUUID());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setDateOfBirth(Date.valueOf(request.getDateOfBirth()));
        entity.setGender(Gender.valueOf(request.getGender()));
        entity.setPhone(request.getPhone());
        entity.setAddress(request.getAddress());
        entity.setSuburb(request.getSuburb());
        entity.setState(request.getState());
        entity.setPostcode(request.getPostcode());

        patientRepository.save(entity);
    }
}
