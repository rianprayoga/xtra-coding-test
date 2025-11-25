package com.xtra.demo.service;

import com.xtra.demo.controller.patient.dto.CreatePatientRequest;
import com.xtra.demo.controller.patient.dto.CreatePatientResponse;
import com.xtra.demo.controller.patient.dto.UpdatePatientRequest;
import com.xtra.demo.data.entity.Gender;
import com.xtra.demo.data.entity.PatientEntity;
import com.xtra.demo.data.repository.PatientRepository;
import com.xtra.demo.errors.http.ConflictException;
import com.xtra.demo.errors.http.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    public static final String FIRST_NAME = "fname";
    public static final String LAST_NAME = "lname";
    public static final String DOB = "1995-01-01";
    public static final String GENDER = "M";
    public static final String PHONE = "987";
    public static final String ADDRESS = "address";
    public static final String SUBURB = "suburb";
    public static final String STATE = "state";
    public static final String POSTCODE = "postcode";

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void addPatient_successfully(){
        CreatePatientRequest request = getRequest();
        PatientEntity entity = patientEntity(request);

        when(patientRepository.phoneNumberExist(PHONE))
                .thenReturn(false);

        when(patientRepository.save(any())).thenReturn(entity);

        CreatePatientResponse actual = patientService.addPatient(request);

        assertThat(actual)
                .returns(FIRST_NAME, CreatePatientResponse::getFirstName)
                .returns(LAST_NAME, CreatePatientResponse::getLastName)
                .returns(DOB, CreatePatientResponse::getDateOfBirth)
                .returns(PHONE, CreatePatientResponse::getPhone)
                .returns(ADDRESS, CreatePatientResponse::getAddress)
                .returns(SUBURB, CreatePatientResponse::getSuburb)
                .returns(0, CreatePatientResponse::getVersion);
    }

    @Test
    void addPatient_throwDuplicatePhone(){
        CreatePatientRequest request = getRequest();

        when(patientRepository.phoneNumberExist(PHONE))
                .thenReturn(true);

        assertThatThrownBy(()->patientService.addPatient(request))
            .isInstanceOf(ConflictException.class);

    }

    @Test
    void getPatient_successfully(){
        String id = UUID.randomUUID().toString();

        PatientEntity entity = patientEntity(getRequest());

        when(patientRepository.findByIdAndActive(
                UUID.fromString(id)
        )).thenReturn(Optional.of(entity));

        CreatePatientResponse actual = patientService.getPatient(id);

        assertThat(actual)
                .returns(FIRST_NAME, CreatePatientResponse::getFirstName)
                .returns(LAST_NAME, CreatePatientResponse::getLastName)
                .returns(DOB, CreatePatientResponse::getDateOfBirth)
                .returns(PHONE, CreatePatientResponse::getPhone)
                .returns(ADDRESS, CreatePatientResponse::getAddress)
                .returns(SUBURB, CreatePatientResponse::getSuburb)
                .returns(0, CreatePatientResponse::getVersion);
    }

    @Test
    void getPatient_throwNotFound(){

        String id = UUID.randomUUID().toString();

        when(patientRepository.findByIdAndActive(
                UUID.fromString(id)
        )).thenReturn(Optional.empty());

        assertThatThrownBy(()->
                patientService.getPatient(id))
                .isInstanceOf(NotFoundException.class);

    }

    @Test
    void updatePatient_successfully(){
        String id = UUID.randomUUID().toString();

        UpdatePatientRequest request = mock();
        when(request.getVersion()).thenReturn(0);
        when(request.getPhone()).thenReturn(PHONE);
        when(request.getDateOfBirth()).thenReturn(DOB);
        when(request.getGender()).thenReturn("M");

        PatientEntity entity = patientEntity(getRequest());

        when(patientRepository.findByIdAndActive(
                UUID.fromString(id)
        )).thenReturn(Optional.of(entity));


        patientService.updatePatient(id,request);

        verify(patientRepository, times(1)).save(any());
    }

    @Test
    void updatePatient_throwNotFound(){
        String id = UUID.randomUUID().toString();

        when(patientRepository.findByIdAndActive(UUID.fromString(id)))
                .thenReturn(Optional.empty());

        assertThatThrownBy(()->  patientService.updatePatient(id, mock()))
                .isInstanceOf(NotFoundException.class);

    }

    @Test
    void updatePatient_throwConflictVersion(){
        String id = UUID.randomUUID().toString();

        UpdatePatientRequest request = mock();
        when(request.getVersion()).thenReturn(1);

        PatientEntity entity = mock();
        when(entity.getVersion()).thenReturn(0);

        when(patientRepository.findByIdAndActive(UUID.fromString(id)))
                .thenReturn(Optional.of(entity));

        assertThatThrownBy(()->
            patientService.updatePatient(id, request))
                .isInstanceOf(ConflictException.class);
    }


    private PatientEntity patientEntity(CreatePatientRequest request){
        PatientEntity entity = mock();
        when(entity.getPid()).thenReturn(UUID.randomUUID());
        when(entity.getFirstName()).thenReturn(request.getFirstName());
        when(entity.getLastName()).thenReturn(request.getLastName());
        when(entity.getDateOfBirth()).thenReturn(Date.valueOf(request.getDateOfBirth()));
        when(entity.getGender()).thenReturn(Gender.valueOf(request.getGender()));
        when(entity.getPhone()).thenReturn(request.getPhone());
        when(entity.getAddress()).thenReturn(request.getAddress());
        when(entity.getSuburb()).thenReturn(request.getSuburb());
        when(entity.getState()).thenReturn(request.getState());
        when(entity.getPostcode()).thenReturn(request.getPostcode());
        when(entity.getVersion()).thenReturn(0);
        when(entity.getCreatedAt()).thenReturn(0L);
        when(entity.getUpdatedAt()).thenReturn(0L);
        return entity;
    }

    private CreatePatientRequest getRequest(){
        return new CreatePatientRequest(
                FIRST_NAME,LAST_NAME, DOB,
                GENDER,
                PHONE, ADDRESS, SUBURB, STATE, POSTCODE
        );
    }
}