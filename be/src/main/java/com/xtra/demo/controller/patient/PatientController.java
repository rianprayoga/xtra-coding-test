package com.xtra.demo.controller.patient;

import com.xtra.demo.controller.patient.dto.CreatePatientRequest;
import com.xtra.demo.controller.patient.dto.CreatePatientResponse;
import com.xtra.demo.service.PatientService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        patientService.addPatient(request);

        return ResponseEntity.ok(new CreatePatientResponse());
    }

}
