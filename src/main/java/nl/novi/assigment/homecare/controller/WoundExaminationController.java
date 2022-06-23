package nl.novi.assigment.homecare.controller;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.service.WoundExaminationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("woundexaminations")
public class WoundExaminationController {

    private final WoundExaminationService woundExaminationService;

    public WoundExaminationController(WoundExaminationService woundExaminationService) {
        this.woundExaminationService = woundExaminationService;
    }

    @GetMapping
    public ResponseEntity<List<WoundExaminationDto>> getAllExams(){
        return ResponseEntity.ok(woundExaminationService.getAllWoundExamination());
    }


    @PutMapping("{id}/assessment")
    public void addAssessmentToWound (@PathVariable Long id, @RequestBody CreateWoundExaminationDto dto){
        woundExaminationService.addAssessmentToWound(id, dto);
    }


}
