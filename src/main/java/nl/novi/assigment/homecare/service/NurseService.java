package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.domain.dto.CreateNurseDto;
import nl.novi.assigment.homecare.domain.dto.CreateWoundDto;
import nl.novi.assigment.homecare.domain.entity.Nurse;
import nl.novi.assigment.homecare.domain.entity.Wound;
import nl.novi.assigment.homecare.repository.NurseRepository;
import org.springframework.stereotype.Service;

@Service
public class NurseService {

    private NurseRepository nurseRepository;
    private WoundService woundService;

    public NurseService(NurseRepository nurseRepository, WoundService woundService) {
        this.nurseRepository = nurseRepository;
        this.woundService = woundService;
    }

    public Nurse addNurse (CreateNurseDto createNurseDto) {
        Nurse nurse = new Nurse();
        nurse.setName(createNurseDto.getName());
        nurse.setBigNumber(createNurseDto.getBigNumber());
        nurse.setEmail(createNurseDto.getEmail());
        nurse.setPassword(createNurseDto.getPassword());
        Nurse savedNurse = nurseRepository.save(nurse);
        return savedNurse;
    }







}
