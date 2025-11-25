package com.xtra.demo.controller.patient;

import com.xtra.demo.controller.patient.dto.CreatePatientRequest;
import com.xtra.demo.controller.patient.dto.CreatePatientResponse;
import com.xtra.demo.controller.patient.dto.UpdatePatientRequest;
import com.xtra.demo.service.PatientService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.xtra.demo.controller.ControllerConstant.V1;

@Validated
@RestController
@RequestMapping(V1)
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patients")
    public ResponseEntity<CreatePatientResponse> addPatient(
            @Valid @RequestBody CreatePatientRequest request ){

        CreatePatientResponse response = patientService.addPatient(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<CreatePatientResponse> addPatient(@PathVariable String patientId){
        CreatePatientResponse response = patientService.getPatient(patientId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/patients/{patientId}")
    public ResponseEntity<CreatePatientResponse> updatePatient(
            @PathVariable String patientId, @RequestBody UpdatePatientRequest request){

        CreatePatientResponse response = patientService.updatePatient(patientId, request);
        return ResponseEntity.ok(response);
    }



}
