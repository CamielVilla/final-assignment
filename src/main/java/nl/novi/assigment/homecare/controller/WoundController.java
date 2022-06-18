package nl.novi.assigment.homecare.controller;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.service.WoundExaminationService;
import nl.novi.assigment.homecare.service.WoundService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

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

//    @GetMapping("/{id}")
//    public ResponseEntity<WoundDto> getWoundById(@PathVariable Long id){
//       return ResponseEntity.ok(woundService.getWoundById(id));
//    }


    @PostMapping("/{id}/photo")
    public void assignPhotoToWoundPhoto(@PathVariable Long id, @RequestBody MultipartFile file){

        FileUploadResponse photo = photoController.singleFileUpload(file);

        woundExaminationService.assignPhotoToWoundExamination(photo.getFileName(), id);
    }

//    @PostMapping("/{id}/photo")
//    public ResponseEntity<WoundDto> addWoundPhoto (@RequestBody CreateWoundExaminationDto createWoundExaminationDto, @PathVariable Long id){
//        WoundDto woundDto = woundService.getWoundById(id);
//        woundService.addWoundExaminationToToWound(woundDto.getId(), createWoundExaminationDto);
//        return ResponseEntity.ok(woundDto);

}



