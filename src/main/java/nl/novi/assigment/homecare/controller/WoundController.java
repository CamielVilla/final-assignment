package nl.novi.assigment.homecare.controller;


import nl.novi.assigment.homecare.model.dto.CreateWoundDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.Patient;
import nl.novi.assigment.homecare.model.entity.Wound;
import nl.novi.assigment.homecare.service.WoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("wounds")
public class WoundController {

    private final WoundService woundService;
    public WoundController(WoundService woundService) {
        this.woundService = woundService;
    }

//    @PostMapping
//    public ResponseEntity<WoundDto> createWound (@RequestBody CreateWoundDto createWoundDto) {
//        final WoundDto woundDto = woundService.addWound(createWoundDto);
//        final URI location = URI.create("wounds");
//        return ResponseEntity
//                .created(location)
//                .body(woundDto);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<WoundDto> getWoundById(@PathVariable Long id){
       return ResponseEntity.ok(woundService.getWoundById(id));
    }
}


