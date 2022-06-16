package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.model.dto.CreatePatientDto;
import nl.novi.assigment.homecare.model.dto.CreateWoundDto;
import nl.novi.assigment.homecare.model.dto.PatientDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.Wound;
import nl.novi.assigment.homecare.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("patients")
public class PatientController {
    private final PatientService patientService;
    private final PhotoController photoController;

    public PatientController(PatientService patientService, PhotoController photoController) {
        this.patientService = patientService;
        this.photoController = photoController;
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

    @PutMapping("{id}/wound")
    public ResponseEntity<PatientDto> createWound (@RequestBody CreateWoundDto createWoundDto, @PathVariable Long id) {
        PatientDto patientDto = patientService.getPatientById(id);
//        List<Wound> wounds = patientDto.getWounds();
        patientService.addWoundToPatient(id, createWoundDto);
        return ResponseEntity.ok(patientDto);
//        final URI location = URI.create("wounds");
//        return ResponseEntity
//                .created(location)
//                .body(woundDto);
    }

}
