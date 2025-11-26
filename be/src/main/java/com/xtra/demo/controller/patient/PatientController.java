package com.xtra.demo.controller.patient;

import com.xtra.demo.controller.PageResult;
import com.xtra.demo.controller.patient.dto.CreatePatientRequest;
import com.xtra.demo.controller.patient.dto.CreatePatientResponse;
import com.xtra.demo.controller.patient.dto.UpdatePatientRequest;
import com.xtra.demo.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.xtra.demo.controller.ControllerConstant.V1;

@Validated
@RestController
@RequestMapping(V1)
public class PatientController {

    private final PatientService patientService;
    private final int defaultOffset;
    private final int defaultSize;

    @Autowired
    public PatientController(
            PatientService patientService,
            @Value("${pagination.offset}") int defaultOffset,
            @Value("${pagination.size}") int defaultSize) {
        this.patientService = patientService;
        this.defaultOffset = defaultOffset;
        this.defaultSize = defaultSize;
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
            @PathVariable String patientId, @Valid @RequestBody UpdatePatientRequest request){

        CreatePatientResponse response = patientService.updatePatient(patientId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/patients/{patientId}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable String patientId){

        patientService.deletePatient(patientId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/patients")
    public ResponseEntity<PageResult<CreatePatientResponse>> getPatients(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "pid", required = false) String pid,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "size", required = false) Integer size
            ){

        offset = offset == null? defaultOffset: offset;
        size = size == null? defaultSize: size;

        PageResult<CreatePatientResponse> response = patientService
                .getPatients(offset, size, firstName, lastName, pid);
        return ResponseEntity.ok(response);
    }
}
