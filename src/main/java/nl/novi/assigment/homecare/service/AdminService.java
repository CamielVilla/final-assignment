package nl.novi.assigment.homecare.service;

import nl.novi.assigment.homecare.domain.dto.AdminDto;
import nl.novi.assigment.homecare.domain.dto.CreateAdminDto;
import nl.novi.assigment.homecare.domain.entity.Admin;
import nl.novi.assigment.homecare.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AdminDto createAdmin(CreateAdminDto createAdminDto) {
        Admin admin = new Admin();
        admin.setName(createAdminDto.getName());
        admin.setEmail(createAdminDto.getEmail());
        admin.setPassword(createAdminDto.getPassword());
        admin.setRole("ADMIN");
        admin.setEnabled(1);
        userRepository.save(admin);
        return toAdminDto(admin);
    }

    private AdminDto toAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setName(admin.getName());
        adminDto.setEmail(admin.getEmail());
        adminDto.setPassword(admin.getPassword());
        adminDto.setEnabled(admin.getEnabled());
        adminDto.setRole(admin.getRole());
        return adminDto;
    }

}
