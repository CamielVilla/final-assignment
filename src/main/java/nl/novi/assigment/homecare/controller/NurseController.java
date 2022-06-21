package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.model.dto.CreateNurseDto;
import nl.novi.assigment.homecare.model.dto.NurseDto;
import nl.novi.assigment.homecare.service.NurseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping
    public ResponseEntity<List<NurseDto>> getAllNurses(){
       return ResponseEntity.ok(nurseService.getAllNurses());
    }
}
