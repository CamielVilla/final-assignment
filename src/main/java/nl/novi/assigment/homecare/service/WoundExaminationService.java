package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.repository.FileUploadRepository;
import nl.novi.assigment.homecare.repository.WoundExaminationRepository;
import nl.novi.assigment.homecare.repository.WoundRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WoundExaminationService {


    private final WoundExaminationRepository woundExaminationRepository;
    private final FileUploadRepository fileUploadRepository;
    private final WoundService woundService;
    private final WoundRepository woundRepository;


    public WoundExaminationService(WoundExaminationRepository woundExaminationRepository, FileUploadRepository fileUploadRepository, WoundService woundService, WoundRepository woundRepository) {
        this.woundExaminationRepository = woundExaminationRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.woundService = woundService;
        this.woundRepository = woundRepository;
    }

    public WoundExaminationDto addWoundExamination(CreateWoundExaminationDto createWoundExaminationDto) {
        WoundExamination woundExamination = new WoundExamination();
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

    public WoundExamination getWoundExaminationById(Long id){
        if (woundExaminationRepository.existsById(id)){
            WoundExamination woundExamination = woundExaminationRepository.findById(id).get();
            return woundExamination;
        }else{
            throw new RuntimeException();
        }
    }


//    public WoundExaminationDto reviewWoundPhoto(CreateWoundExaminationDto createWoundExaminationDto, Long id) {
//        WoundExamination woundExamination = getWoundExaminationById(id);
//        woundExamination.setNurseAssessment(createWoundExaminationDto.getNurseAssessment());
//        woundExamination.setAssessmentDate(LocalDateTime.now());
//        woundExaminationRepository.save(woundExamination);
//        return toWoundExaminationDto(woundExamination);
//    }
//
//    public WoundExaminationDto addPatientComment(Long id, CreateWoundExaminationDto createWoundExaminationDto){
//        WoundExaminationDto woundExaminationDto = toWoundExaminationDto(getWoundExaminationById(id));
//        woundExaminationDto.setPatientComment(createWoundExaminationDto.getPatientComment());
//        WoundExamination savedWoundExamination = woundExaminationRepository.save(toWoundExamination(woundExaminationDto));
//        return toWoundExaminationDto(savedWoundExamination);
//    }

    public void assignPhotoToWoundExamination(String name, Long woundId)
    {
        WoundDto woundDto = woundService.getWoundById(woundId);
        List<WoundExamination> woundExaminations = woundDto.getWoundExamination();
        WoundExamination woundExamination = new WoundExamination();
        woundExamination.setWound(woundService.toWound(woundDto));
        woundExaminations.add(woundExamination);
        woundDto.setWoundExamination(woundExaminations);


        Optional<FileUploadResponse> optionalFileUploadResponse = fileUploadRepository.findByFileName(name);

        if (optionalFileUploadResponse.isPresent()) {
            FileUploadResponse photo = optionalFileUploadResponse.get();
            woundExamination.setFile(photo);
            woundExaminationRepository.save(woundExamination);
        }
    }


}
