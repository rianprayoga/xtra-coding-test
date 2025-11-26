package org.example;

import java.util.*;

public class MasterPatientIndex {

    private PatientDemographicRepo demographicRepo;
    private PatientLinkingRepo linkingRepo;
    private final int MAX_SCORE = 5;

    public Result addPatient(NewPatient newPatient){
        final int MAX_SCORE = 5;

        List<ExistingPatient> patients =
                demographicRepo.findPatients(newPatient);

        int newPatientId = demographicRepo.save(newPatient);

        Map.Entry<ExistingPatient, Integer> matched = match(newPatient, patients);
        if (matched == null){
            return Result.NO_MATCH;
        }

        if (matched.getValue() < MAX_SCORE/2){
            return Result.REVIEW;
        }

        int existingPatientId = matched.getKey().id();
        PatientLinking group = linkingRepo.findLinking(existingPatientId);
        if (group != null){
            group.linking().add(newPatientId);
            linkingRepo.save(group);
            return Result.AUTO_MATCH;
        }

        linkingRepo.save(
                new PatientLinking(List.of(newPatientId,existingPatientId))
        );

        return Result.AUTO_MATCH;
    }

    public Map.Entry<ExistingPatient, Integer> match(
            NewPatient newPatient, List<ExistingPatient> existingPatients){

        Map<ExistingPatient, Integer> scoring = new HashMap<>();

        existingPatients.forEach( e -> {
            int score = 0;

            if (e.firstName().equals(newPatient.firstName())) score++;
            if (e.lastName().equals(newPatient.lastName())) score++;
            if (e.dob().equals(newPatient.dob())) score++;
            if (e.email().equals(newPatient.dob())) score++;
            if (e.phone().equals(newPatient.phone())) score++;

            scoring.put(e, score);
        });
        List<Map.Entry<ExistingPatient, Integer>> sorted = scoring
                .entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .toList();


        if (sorted.isEmpty()) return null;
        return sorted.get(0);
    }
}
