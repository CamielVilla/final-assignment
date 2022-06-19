package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.model.dto.*;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
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

//@CrossOrigin
@RestController
@RequestMapping("patients")
public class PatientController {
    private final PatientService patientService;
    private final WoundService woundService;
    private final WoundExaminationService woundExaminationService;
    private final PhotoController photoController;

    public PatientController(PatientService patientService, WoundService woundService, WoundExaminationService woundExaminationService, PhotoController photoController) {
        this.patientService = patientService;
        this.woundService = woundService;
        this.woundExaminationService = woundExaminationService;
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

    @PostMapping("{id}/wound")
    public ResponseEntity<PatientDto> addWound (@RequestBody CreateWoundDto createWoundDto, @PathVariable Long id) {
        PatientDto patientDto = patientService.getPatientById(id);
        patientService.addWoundToPatient(patientDto.getId(), createWoundDto);
        return ResponseEntity.ok(patientDto);
    }

//    @PostMapping("{id}/{woundId}/photo")
//    public void assignPhotoToWoundPhoto(@PathVariable Long id, @PathVariable Long woundId, @RequestBody MultipartFile file){
//
//        FileUploadResponse photo = photoController.singleFileUpload(file);
//
//        woundExaminationService.assignPhotoToWoundExamination(photo.getFileName(), woundId);
//    }



//    @GetMapping("/{id}/{woundId}")
//    public ResponseEntity<WoundDto> getWoundById(@PathVariable Long id, @PathVariable Long woundId){
//        return ResponseEntity.ok(woundService.getWoundById(woundId));
//    }

    @GetMapping("{id}/wounds")
    public ResponseEntity<List<Wound>> getAllWounds (@PathVariable Long id){
       return ResponseEntity.ok(patientService.getAllWoundsFromPatient(id));
    }


}
