package nl.novi.assigment.homecare.controller;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.service.WoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wounds")
public class WoundController {

    private final WoundService woundService;
    public WoundController(WoundService woundService) {
        this.woundService = woundService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<WoundDto> getWoundById(@PathVariable Long id){
       return ResponseEntity.ok(woundService.getWoundById(id));
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<WoundDto> addWoundPhoto (@RequestBody CreateWoundExaminationDto createWoundExaminationDto, @PathVariable Long id){
        WoundDto woundDto = woundService.getWoundById(id);
        woundService.addWoundExaminationToToWound(woundDto.getId(), createWoundExaminationDto);
        return ResponseEntity.ok(woundDto);

    }
}


