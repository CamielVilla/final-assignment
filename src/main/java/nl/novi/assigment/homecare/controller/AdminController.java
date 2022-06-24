package nl.novi.assigment.homecare.controller;


import nl.novi.assigment.homecare.model.dto.*;
import nl.novi.assigment.homecare.model.entity.User;
import nl.novi.assigment.homecare.service.AdminService;
import nl.novi.assigment.homecare.service.PatientService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {
    private final AdminService adminService;
    private final PatientService patientService;

    public AdminController(AdminService adminService, PatientService patientService) {
        this.adminService = adminService;
        this.patientService = patientService;
    }

//    @PostMapping
//    public ResponseEntity<AdminDto> createAdmin (@RequestBody  CreateAdminDto createAdminDto){
//        final AdminDto adminDto = adminService.createAdmin(createAdminDto);
//        final URI location = URI.create("admin");
//        return ResponseEntity
//                .created(location)
//                .body(adminDto);
//    }

    @PostMapping("addpatient")
    public ResponseEntity<PatientDto> addPatient (@RequestBody CreatePatientDto createPatientDto){
        final PatientDto patientDto = adminService.addPatient(createPatientDto);
        final URI location = URI.create("patients" + patientDto.getId());
        return ResponseEntity
                .created(location)
                .body(patientDto);
    }

    @GetMapping("patients")
    ResponseEntity <List<PatientDto>>getAllPatients(){
        return ResponseEntity.ok(adminService.getAllPatients());
    }

    @GetMapping("nurses")
    ResponseEntity <List<NurseDto>> getAllNurses(){
        return ResponseEntity.ok(adminService.getAllNurses());
    }

    @CrossOrigin
    @PostMapping("addwound/{id}")
    public ResponseEntity<PatientDto> addWound (@RequestBody CreateWoundDto createWoundDto, @PathVariable Long id) {
        PatientDto patientDto = patientService.getPatientById(id);
        adminService.addWoundToPatient(patientDto.getId(), createWoundDto);
        return ResponseEntity.ok(patientDto);
    }

    @PostMapping("addnurse")
    public ResponseEntity<NurseDto> addNurse (@RequestBody CreateNurseDto createNurseDto) {
        final NurseDto nurseDto = adminService.addNurse(createNurseDto);
        return ResponseEntity.ok(nurseDto);
    }


}
