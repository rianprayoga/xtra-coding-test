package com.xtra.demo.service;

import com.xtra.demo.controller.PageResult;
import com.xtra.demo.controller.patient.dto.CreatePatientRequest;
import com.xtra.demo.controller.patient.dto.CreatePatientResponse;
import com.xtra.demo.controller.patient.dto.UpdatePatientRequest;
import com.xtra.demo.data.entity.Gender;
import com.xtra.demo.data.entity.PatientEntity;
import com.xtra.demo.data.repository.PatientRepository;
import com.xtra.demo.errors.http.BadRequestException;
import com.xtra.demo.errors.http.ConflictException;
import com.xtra.demo.errors.http.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public CreatePatientResponse addPatient(CreatePatientRequest request){

        if (patientRepository.phoneNumberExist(request.getPhone())){
            throw new ConflictException("Phone number %s already registered.".formatted(request.getPhone()));
        }

        PatientEntity entity = new PatientEntity();
        entity.setPid(UUID.randomUUID());
        setBasicAttribute(entity, request);
        entity.setCreatedAt(System.currentTimeMillis());
        entity.setUpdatedAt(System.currentTimeMillis());
        entity.setActive(true);

        PatientEntity patientEntity = patientRepository.save(entity);
        return CreatePatientResponse.from(patientEntity);
    }

    private UUID parseString(String id){
        try {
            return UUID.fromString(id);
        }catch (Exception e){
            throw new BadRequestException("Invalid id format.");
        }
    }

    public CreatePatientResponse getPatient(String id){

        PatientEntity entity = patientRepository
                .findByIdAndActive(parseString(id))
                .orElseThrow(() -> new NotFoundException("Patient with id %s not found.".formatted(id)));

        return CreatePatientResponse.from(entity);
    }

    public CreatePatientResponse updatePatient(String id, UpdatePatientRequest request){

        PatientEntity entity = patientRepository
                .findByIdAndActive(parseString(id))
                .orElseThrow(() -> new NotFoundException("Patient with id %s not found.".formatted(id)));

        if (entity.getVersion() != request.getVersion()){
            throw new ConflictException("Mismatch version.");
        }

        if (!entity.getPhone().equals(request.getPhone())){
            if (patientRepository.phoneNumberExist(request.getPhone())){
                throw new ConflictException("Phone number %s already registered.".formatted(request.getPhone()));
            }
        }

        setBasicAttribute(entity, request);
        entity.setUpdatedAt(System.currentTimeMillis());
        entity.setVersion(entity.getVersion() + 1);
        patientRepository.save(entity);

        return CreatePatientResponse.from(entity);
    }

    private void setBasicAttribute(PatientEntity entity, CreatePatientRequest request){
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setDateOfBirth(Date.valueOf(request.getDateOfBirth()));
        entity.setGender(Gender.valueOf(request.getGender()));
        entity.setPhone(request.getPhone());
        entity.setAddress(request.getAddress());
        entity.setSuburb(request.getSuburb());
        entity.setState(request.getState());
        entity.setPostcode(request.getPostcode());
    }

    @Transactional
    public void deletePatient(String id){
        UUID uuid = parseString(id);
        patientRepository.inactivePatient(uuid);
    }


    public PageResult<CreatePatientResponse> getPatients(
            Integer page, Integer size, String firstName, String lastName, String pid) {

        List<PatientEntity> entities = patientRepository
                .findAll(firstName, lastName, pid, page, size);
        long count = patientRepository.count(firstName, lastName, pid);

        List<CreatePatientResponse> content = entities
                .stream().map(CreatePatientResponse::partial).toList();

        return new PageResult<>(content, count);
    }
}
