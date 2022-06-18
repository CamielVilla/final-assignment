package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.model.dto.*;
import nl.novi.assigment.homecare.service.PatientService;
import nl.novi.assigment.homecare.service.WoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("patients")
public class PatientController {
    private final PatientService patientService;
    private final WoundService woundService;

    public PatientController(PatientService patientService, WoundService woundService) {
        this.patientService = patientService;
        this.woundService = woundService;
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
    @Transactional
    ResponseEntity<PatientDto> getPatient (@PathVariable Long id) {
        PatientDto patientDto = patientService.getPatientById(id);
        return ResponseEntity.ok(patientDto);
    }

    @GetMapping
    ResponseEntity <List<PatientDto>>getAllPatients(){
        List<PatientDto> patientDtoList = patientService.getAllPatients();
        return ResponseEntity.ok(patientDtoList);
    }

    @PutMapping("{id}")
    ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody CreatePatientDto createPatientDto)  {
        patientService.updatePatient(id, createPatientDto);
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping("{id}/wound")
    public ResponseEntity<PatientDto> addWound (@RequestBody CreateWoundDto createWoundDto, @PathVariable Long id) {
        PatientDto patientDto = patientService.getPatientById(id);
        patientService.addWoundToPatient(patientDto.getId(), createWoundDto);
        return ResponseEntity.ok(patientDto);
    }

    @GetMapping("/{id}/{woundId}")
    public ResponseEntity<WoundDto> getWoundById(@PathVariable Long id, @PathVariable Long woundId){
        return ResponseEntity.ok(woundService.getWoundById(woundId));
    }

}
