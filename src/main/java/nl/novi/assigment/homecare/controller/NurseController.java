package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.domain.dto.CreateNurseDto;
import nl.novi.assigment.homecare.domain.dto.NurseDto;
import nl.novi.assigment.homecare.domain.entity.Nurse;
import nl.novi.assigment.homecare.service.NurseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nurses")
public class NurseController {
    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @PostMapping
    public ResponseEntity<NurseDto> addNurse (@RequestBody CreateNurseDto createNurseDto) {
        final NurseDto nurseDto = nurseService.addNurse(createNurseDto);
        return ResponseEntity.ok(nurseDto);
    }
}
