package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.repository.FileUploadRepository;
import nl.novi.assigment.homecare.repository.WoundExaminationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WoundExaminationService {


    private final WoundExaminationRepository woundExaminationRepository;
    private final WoundService woundService;
    private final FileUploadRepository fileUploadRepository;

    public WoundExaminationService(WoundExaminationRepository woundExaminationRepository, WoundService woundService, FileUploadRepository fileUploadRepository) {
        this.woundExaminationRepository = woundExaminationRepository;
        this.woundService = woundService;
        this.fileUploadRepository = fileUploadRepository;
    }


    public WoundExaminationDto addWoundExamination(CreateWoundExaminationDto createWoundExaminationDto) {
        WoundExamination woundExamination = new WoundExamination();
        woundExamination.setPhotoDate(LocalDate.now());
        woundExamination.setPatientComment(createWoundExaminationDto.getPatientComment());
        woundExaminationRepository.save(woundExamination);
        return toWoundExaminationDto(woundExamination);
    }

    public WoundExaminationDto toWoundExaminationDto(WoundExamination woundExamination) {
        WoundExaminationDto woundExaminationDto = new WoundExaminationDto();
        woundExaminationDto.setId(woundExamination.getId());
        woundExaminationDto.setNurseAssessment(woundExamination.getNurseAssessment());
        woundExaminationDto.setAssessmentDate(woundExamination.getAssessmentDate());
        woundExaminationDto.setPatientComment(woundExamination.getPatientComment());
        woundExaminationDto.setPhotoDate(woundExamination.getPhotoDate());
        woundExaminationDto.setWound(woundExamination.getWound());
        return woundExaminationDto;
    }

    public WoundExamination toWoundExamination(WoundExaminationDto woundExaminationDto){
        WoundExamination woundExamination = new WoundExamination();
        woundExamination.setId(woundExaminationDto.getId());
        woundExamination.setNurseAssessment(woundExaminationDto.getNurseAssessment());
        woundExamination.setAssessmentDate(woundExaminationDto.getAssessmentDate());
        woundExamination.setPatientComment(woundExaminationDto.getPatientComment());
        woundExamination.setPhotoDate(woundExaminationDto.getPhotoDate());
        woundExamination.setWound(woundExaminationDto.getWound());
        return woundExamination;
    }

    public WoundExaminationDto getWoundExaminationDtoById(Long id){
        if (woundExaminationRepository.existsById(id)){
            WoundExaminationDto woundExaminationDto = toWoundExaminationDto(woundExaminationRepository.findById(id).get());
            return woundExaminationDto;
        }else{
            throw new RuntimeException();
        }
    }


    public WoundExaminationDto reviewWoundPhoto(CreateWoundExaminationDto createWoundExaminationDto, Long id) {
        WoundExamination woundExamination = toWoundExamination(getWoundExaminationDtoById(id));
        woundExamination.setNurseAssessment(createWoundExaminationDto.getNurseAssessment());
        woundExamination.setAssessmentDate(LocalDateTime.now());
        woundExaminationRepository.save(woundExamination);
        return toWoundExaminationDto(woundExamination);
    }

//    public void assignPhotoToWoundExamination(String name, Long id) {
//        WoundExamination woundExamination = toWoundExamination(getWoundExaminationDtoById(id));
//        Optional<WoundExamination> optionalFileUploadResponse = fileUploadRepository.findByFileName(name);
//
//        if (optionalFileUploadResponse.isPresent()) {
//            WoundExamination photo = optionalFileUploadResponse.get();
//            woundExamination.setFile(photo);
//            woundExaminationRepository.save(woundExamination);
//        }
//    }
}
