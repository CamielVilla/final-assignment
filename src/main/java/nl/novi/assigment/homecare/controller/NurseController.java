package nl.novi.assigment.homecare.controller;
import nl.novi.assigment.homecare.service.NurseService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("nurses")
public class NurseController {
    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

}
