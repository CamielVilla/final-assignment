package nl.novi.assigment.homecare.controller;


import nl.novi.assigment.homecare.domain.dto.AdminDto;
import nl.novi.assigment.homecare.domain.dto.CreateAdminDto;
import nl.novi.assigment.homecare.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<AdminDto> createAdmin (@RequestBody  CreateAdminDto createAdminDto){
        final AdminDto adminDto = adminService.createAdmin(createAdminDto);
        final URI location = URI.create("admin");
        return ResponseEntity
                .created(location)
                .body(adminDto);
    }
}
