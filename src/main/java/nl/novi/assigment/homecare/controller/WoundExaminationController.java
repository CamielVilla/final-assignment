package nl.novi.assigment.homecare.controller;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.service.WoundExaminationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("woundexamination")
public class WoundExaminationController {

    private final WoundExaminationService woundExaminationService;

    public WoundExaminationController(WoundExaminationService woundExaminationService) {
        this.woundExaminationService = woundExaminationService;
    }


}
