package nl.novi.assigment.homecare.controller;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.service.WoundExaminationService;
import nl.novi.assigment.homecare.service.WoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("wounds")
public class WoundController {

    private final WoundService woundService;
    private final PhotoController photoController;
    private final WoundExaminationService woundExaminationService;

    public WoundController(WoundService woundService, PhotoController photoController, WoundExaminationService woundExaminationService) {
        this.woundService = woundService;
        this.photoController = photoController;
        this.woundExaminationService = woundExaminationService;
    }

    @GetMapping("{id}")
    public ResponseEntity<WoundDto> getWoundById(@PathVariable Long id){
       return ResponseEntity.ok(woundService.getWoundById(id));
    }


    @PostMapping("{id}/photo")
    public ResponseEntity<WoundExaminationDto> addPhoto (@PathVariable Long id, @RequestBody MultipartFile file){
                FileUploadResponse photo = photoController.singleFileUpload(file);
              return  ResponseEntity.ok(woundService.addPhotoToWound(photo.getFileName(), id));


    }

        @PutMapping("/assessment/{id}/{examId}/")
    public ResponseEntity<List<WoundExaminationDto>> addAssessmentToWound (@PathVariable Long id, @PathVariable Long examId, @RequestBody CreateWoundExaminationDto dto){
        return ResponseEntity.ok(woundService.addAssessmentToWound(examId, dto));
    }

    @GetMapping("{id}/exams")
    public ResponseEntity<List<WoundExaminationDto>> getAllWoundExamsFromWound (@PathVariable Long id){

       return ResponseEntity.ok(woundService.getAllWoundExamDtosFromWound(id));
    }





    @GetMapping("toassess")
    public ResponseEntity<Set<WoundDto>> getWoundsToAsses (){
        return ResponseEntity.ok(woundService.getWoundsToAsses());
    }


}



