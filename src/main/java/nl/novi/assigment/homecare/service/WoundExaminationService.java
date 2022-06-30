package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.repository.WoundExaminationRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class WoundExaminationService {


    private final WoundExaminationRepository woundExaminationRepository;

    public WoundExaminationService(WoundExaminationRepository woundExaminationRepository) {
        this.woundExaminationRepository = woundExaminationRepository;
    }

    public WoundExaminationDto toWoundExaminationDto(WoundExamination woundExamination) {
        WoundExaminationDto woundExaminationDto = new WoundExaminationDto();
        woundExaminationDto.setId(woundExamination.getId());
        woundExaminationDto.setNurseAssessment(woundExamination.getNurseAssessment());
        woundExaminationDto.setAssessmentDate(woundExamination.getAssessmentDate());
        woundExaminationDto.setPatientComment(woundExamination.getPatientComment());
        woundExaminationDto.setPhotoDate(woundExamination.getPhotoDate());
        woundExaminationDto.setWound(woundExamination.getWound());
        woundExaminationDto.setFile(woundExamination.getFile());
        return woundExaminationDto;
    }

    public WoundExamination toWoundExamination(WoundExaminationDto dto){
        WoundExamination woundExamination = new WoundExamination();
        woundExamination.setId(dto.getId());
        woundExamination.setNurseAssessment(dto.getNurseAssessment());
        woundExamination.setAssessmentDate(dto.getAssessmentDate());
        woundExamination.setPatientComment(dto.getPatientComment());
        woundExamination.setPhotoDate(dto.getPhotoDate());
        woundExamination.setWound(dto.getWound());
        woundExamination.setFile(dto.getFile());
        return woundExamination;
    }

    public WoundExamination saveWoundExamination (WoundExamination woundExamination){
       return woundExaminationRepository.save(woundExamination);
    }

    public List<WoundExaminationDto> getAllWoundExamination (){
       List<WoundExamination> woundExaminations = woundExaminationRepository.findAll();
       List<WoundExaminationDto> dtos = new ArrayList<>();
       for (WoundExamination w : woundExaminations){
           dtos.add(toWoundExaminationDto(w));
       }
       return dtos;
    }

    public WoundExaminationDto getWoundExaminationById (Long id){
        if (woundExaminationRepository.existsById(id)){
            return toWoundExaminationDto(woundExaminationRepository.findById(id).get());
        }else{
            throw new RuntimeException();
        }
    }



}
