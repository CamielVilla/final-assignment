package nl.novi.assigment.homecare.controller;
import nl.novi.assigment.homecare.service.WoundExaminationService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("woundexaminations")
public class WoundExaminationController {
private final WoundExaminationService woundExaminationService;

    public WoundExaminationController(WoundExaminationService woundExaminationService) {
        this.woundExaminationService = woundExaminationService;
    }
}
