package nl.novi.assigment.homecare.controller;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.service.WoundExaminationService;
import nl.novi.assigment.homecare.service.WoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public void addPhoto (@PathVariable Long id, @RequestBody MultipartFile file){
                FileUploadResponse photo = photoController.singleFileUpload(file);
                woundService.addPhotoToWound(photo.getFileName(), id);
    }

    @GetMapping("toassess")
    public ResponseEntity<Set<WoundDto>> getWoundsToAsses (){
        return ResponseEntity.ok(woundService.getWoundsToAsses());
    }


}



