package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.model.dto.CreateNurseDto;
import nl.novi.assigment.homecare.model.dto.NurseDto;
import nl.novi.assigment.homecare.model.entity.Nurse;
import nl.novi.assigment.homecare.repository.NurseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Nurse toNurse(NurseDto dto){
        Nurse nurse = new Nurse();
        nurse.setName(dto.getName());
        nurse.setEmail(dto.getEmail());
        nurse.setId(dto.getId());
        nurse.setPassword(dto.getPassword());
        nurse.setBigNumber(dto.getBigNumber());
        nurse.setRole(dto.getRole());
        nurse.setEnabled(dto.getEnabled());
        return nurse;
    }

    public Nurse saveNurse(Nurse nurse){
        return nurseRepository.save(nurse);
    }



    public List<NurseDto> getAllNurses(){
       List<Nurse> nurses = nurseRepository.findAll();
       List<NurseDto> dtos = new ArrayList<>();
       for (Nurse n: nurses){
           dtos.add(toNurseDto(n));
       }
       return dtos;
    }







}
