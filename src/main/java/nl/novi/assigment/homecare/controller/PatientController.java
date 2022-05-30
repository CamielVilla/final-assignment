package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.domain.dto.CreatePatientDto;
import nl.novi.assigment.homecare.domain.dto.CreateWoundDto;
import nl.novi.assigment.homecare.domain.entity.Patient;
import nl.novi.assigment.homecare.domain.entity.Wound;
import nl.novi.assigment.homecare.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient (@RequestBody CreatePatientDto createPatientDto){
        final Patient patient = patientService.addPatient(createPatientDto);
        final URI location = URI.create("patients" + patient.getId());

        return ResponseEntity
                .created(location)
                .body(patient);
    }

    @GetMapping("{id}")
    ResponseEntity<Patient> getPatient (@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

}
