package nl.novi.assigment.homecare.controller;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.service.WoundExaminationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("woundexamination")
public class WoundExaminationController {

    private final WoundExaminationService woundExaminationService;
    private final PhotoController photoController;

    public WoundExaminationController(WoundExaminationService woundExaminationService, PhotoController photoController) {
        this.woundExaminationService = woundExaminationService;
        this.photoController = photoController;
    }



        @PostMapping
    public ResponseEntity<WoundExaminationDto> addWoundExamination(){
        final WoundExaminationDto woundExaminationDto = woundExaminationService.addWoundExamination();
        final URI location = URI.create("woundexamination");
        return ResponseEntity
                .created(location)
                .body(woundExaminationDto);
    }

//    @PutMapping("{id}")
//    public ResponseEntity<WoundExaminationDto> reviewWoundPhoto (@RequestBody CreateWoundExaminationDto createWoundExaminationDto, @PathVariable Long id) {
//        final WoundExaminationDto woundExaminationDto = woundExaminationService.reviewWoundPhoto(createWoundExaminationDto, id);
//        return ResponseEntity.ok(woundExaminationDto);
//    }




//
//    @PutMapping("{id}/comment")
//    public ResponseEntity<String> addPatientComment (@PathVariable Long id, @RequestBody CreateWoundExaminationDto createWoundExaminationDto){
//      WoundExaminationDto woundExaminationDto = woundExaminationService.addPatientComment(id, createWoundExaminationDto);
//        return ResponseEntity.ok(woundExaminationDto.getPatientComment());
//    }


}
