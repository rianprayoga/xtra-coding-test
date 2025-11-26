package org.example;

public interface PatientLinkingRepo {

    PatientLinking findLinking(int id);
    void save(PatientLinking patientLinking);

}
