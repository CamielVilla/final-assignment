package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.model.dto.*;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.Patient;
import nl.novi.assigment.homecare.model.entity.Wound;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.service.PatientService;
import nl.novi.assigment.homecare.service.WoundExaminationService;
import nl.novi.assigment.homecare.service.WoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Set;

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



    @GetMapping("{id}")
    ResponseEntity<PatientDto> getPatient (@PathVariable Long id) {
        PatientDto patientDto = patientService.getPatientById(id);
        return ResponseEntity.ok(patientDto);
    }


//
//    @PutMapping("{id}")
//    ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody CreatePatientDto createPatientDto)  {
//        patientService.updatePatient(id, createPatientDto);
//        return ResponseEntity.ok(patientService.getPatientById(id));
//    }
//



    @GetMapping("{id}/wounds")
    public ResponseEntity<Set<Wound>> getAllWoundsFromPatient (@PathVariable Long id){
       return ResponseEntity.ok(patientService.getAllWoundsFromPatient(id));
    }
}
