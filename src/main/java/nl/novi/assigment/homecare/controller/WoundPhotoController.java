package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.domain.dto.CreateWoundPhotoDto;
import nl.novi.assigment.homecare.domain.dto.WoundPhotoDto;
import nl.novi.assigment.homecare.domain.entity.WoundPhoto;
import nl.novi.assigment.homecare.service.WoundPhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("woundphoto")
public class WoundPhotoController {

    private final WoundPhotoService woundPhotoService;
    public WoundPhotoController(WoundPhotoService woundPhotoService) {
        this.woundPhotoService = woundPhotoService;
    }

    @PostMapping
    public ResponseEntity<WoundPhotoDto> addWoundPhoto (@RequestBody  CreateWoundPhotoDto createWoundPhotoDto){
        final WoundPhotoDto woundPhotoDto = woundPhotoService.addWoundPhoto(createWoundPhotoDto);
        final URI location = URI.create("woundphoto");
        return ResponseEntity
                .created(location)
                .body(woundPhotoDto);
    }


}
