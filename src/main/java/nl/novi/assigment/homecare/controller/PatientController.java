package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.domain.dto.CreatePatientDto;
import nl.novi.assigment.homecare.domain.dto.PatientDto;
import nl.novi.assigment.homecare.domain.entity.Patient;
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
    public ResponseEntity<PatientDto> addPatient (@RequestBody CreatePatientDto createPatientDto){
        final PatientDto patientDto = patientService.addPatient(createPatientDto);
        final URI location = URI.create("patients" + patientDto.getId());
        return ResponseEntity
                .created(location)
                .body(patientDto);
    }

    @GetMapping("{id}")
    ResponseEntity<PatientDto> getPatient (@PathVariable Long id) {
        PatientDto patientDto = patientService.toPatientDto(patientService.getPatientById(id));
        return ResponseEntity.ok(patientDto);
    }

}
