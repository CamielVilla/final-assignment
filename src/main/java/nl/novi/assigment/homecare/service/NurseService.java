package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.domain.dto.CreateNurseDto;
import nl.novi.assigment.homecare.domain.dto.CreateWoundDto;
import nl.novi.assigment.homecare.domain.dto.NurseDto;
import nl.novi.assigment.homecare.domain.entity.Nurse;
import nl.novi.assigment.homecare.domain.entity.Wound;
import nl.novi.assigment.homecare.repository.NurseRepository;
import org.springframework.stereotype.Service;

@Service
public class NurseService {

    private NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    public NurseDto addNurse (CreateNurseDto createNurseDto) {
        Nurse nurse = new Nurse();
        nurse.setName(createNurseDto.getName());
        nurse.setBigNumber(createNurseDto.getBigNumber());
        nurse.setEmail(createNurseDto.getEmail());
        nurse.setPassword(createNurseDto.getPassword());
        nurse.setEnabled(1);
        nurse.setRole("NURSE");
        Nurse savedNurse = nurseRepository.save(nurse);
        return toNurseDto(savedNurse);
    }

    public NurseDto toNurseDto (Nurse nurse){
        NurseDto nurseDto = new NurseDto();
        nurseDto.setName(nurse.getName());
        nurseDto.setEmail(nurse.getEmail());
        nurseDto.setId(nurse.getId());
        nurseDto.setPassword(nurse.getPassword());
        nurseDto.setBigNumber(nurse.getBigNumber());
        nurseDto.setRole(nurse.getRole());
        nurseDto.setEnabled(nurse.getEnabled());
        return nurseDto;
    }







}
