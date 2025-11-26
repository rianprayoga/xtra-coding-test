package org.example;

import java.util.List;

public interface PatientDemographicRepo {

    List<ExistingPatient> findPatients(NewPatient newPatient);
    int save(NewPatient newPatient);
}
