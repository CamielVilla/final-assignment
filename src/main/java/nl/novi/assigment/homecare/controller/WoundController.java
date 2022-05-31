package nl.novi.assigment.homecare.controller;


import nl.novi.assigment.homecare.domain.dto.CreateWoundDto;
import nl.novi.assigment.homecare.domain.dto.WoundDto;
import nl.novi.assigment.homecare.domain.entity.Patient;
import nl.novi.assigment.homecare.domain.entity.Wound;
import nl.novi.assigment.homecare.service.WoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/wounds")
public class WoundController {

    private final WoundService woundService;
    public WoundController(WoundService woundService) {
        this.woundService = woundService;
    }

    @PostMapping
    public ResponseEntity<WoundDto> createWound (@RequestBody CreateWoundDto createWoundDto) {
        final WoundDto woundDto = woundService.addWound(createWoundDto);
        final URI location = URI.create("/wounds");
        return ResponseEntity
                .created(location)
                .body(woundDto);
    }
}


// wond al bij nieuwe patient
// patientenoverdracht naar Homecare
//account aanmaken met wond
//patientwond dto kan je opslitsen


