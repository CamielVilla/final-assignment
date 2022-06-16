package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.model.dto.CreateWoundPhotoDto;
import nl.novi.assigment.homecare.model.dto.WoundPhotoDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.service.WoundPhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("woundphotos")
public class WoundPhotoController {

    private final WoundPhotoService woundPhotoService;
    private final PhotoController photoController;


    public WoundPhotoController(WoundPhotoService woundPhotoService, PhotoController photoController) {
        this.woundPhotoService = woundPhotoService;
        this.photoController = photoController;
    }

    @PostMapping
    public ResponseEntity<WoundPhotoDto> addWoundPhoto (@RequestBody  CreateWoundPhotoDto createWoundPhotoDto){
        final WoundPhotoDto woundPhotoDto = woundPhotoService.addWoundPhoto(createWoundPhotoDto);
        final URI location = URI.create("woundphotos");
        return ResponseEntity
                .created(location)
                .body(woundPhotoDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<WoundPhotoDto> reviewWoundPhoto (@RequestBody CreateWoundPhotoDto createWoundPhotoDto, @PathVariable Long id) {
        final WoundPhotoDto woundPhotoDto = woundPhotoService.reviewWoundPhoto(createWoundPhotoDto, id);
        return ResponseEntity.ok(woundPhotoDto);
    }


    @PostMapping("/{id}/photo")
    public void assignPhotoToWoundPhoto(@PathVariable Long id, @RequestBody MultipartFile file){

        FileUploadResponse photo = photoController.singleFileUpload(file);

        woundPhotoService.assignPhotoToWoundPhoto(photo.getFileName(), id);
    }

}
