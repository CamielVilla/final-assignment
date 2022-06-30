package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.Wound;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.repository.WoundRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class WoundService {
    private final WoundRepository woundRepository;
    private final WoundExaminationService woundExaminationService;
    private final FileUploadService fileUploadService;

    public WoundService(WoundRepository woundRepository, WoundExaminationService woundExaminationService, FileUploadService fileUploadService) {
        this.woundRepository = woundRepository;
        this.woundExaminationService = woundExaminationService;
        this.fileUploadService = fileUploadService;
    }

    public WoundDto getWoundById (Long id){
        WoundDto woundDto = toWoundDto(woundRepository.findById(id).get());
        return woundDto;
    }

    public WoundDto toWoundDto (Wound wound){
        WoundDto woundDto = new WoundDto();
        woundDto.setId(wound.getId());
        woundDto.setWoundName(wound.getWoundName());
        woundDto.setWoundLocation(wound.getWoundLocation());
        woundDto.setTreatmentPlan(wound.getTreatmentPlan());
        woundDto.setPatient(wound.getPatient());
        woundDto.setWoundExaminations(wound.getWoundExaminations());
        return woundDto;
    }

    public Wound toWound(WoundDto woundDto){
        Wound wound = new Wound();
        wound.setId(woundDto.getId());
        wound.setWoundName(woundDto.getWoundName());
        wound.setWoundLocation(woundDto.getWoundLocation());
        wound.setWoundExaminations(woundDto.getWoundExaminations());
        wound.setTreatmentPlan(woundDto.getTreatmentPlan());
        wound.setPatient(woundDto.getPatient());
        return wound;
    }


    public WoundExaminationDto addPhotoToWound(String name, Long woundId) {

        FileUploadResponse photo = fileUploadService.getFileByName(name);

        if (woundRepository.existsById(woundId)) {
            WoundDto woundDto = getWoundById(woundId);
            List<WoundExamination> woundExaminations = woundDto.getWoundExaminations();

            WoundExamination woundExamination = new WoundExamination();
            woundExamination.setWound(toWound(woundDto));
            woundExamination.setPhotoDate(LocalDate.now());
            woundExamination.setFile(photo);

            woundExaminationService.saveWoundExamination(woundExamination);

            woundExaminations.add(woundExamination);
            woundDto.setWoundExaminations(woundExaminations);
            woundRepository.save(toWound(woundDto));
            return woundExaminationService.toWoundExaminationDto(woundExamination);
        }else throw new RuntimeException();
    }

        public List<WoundExaminationDto> addAssessmentToWound (Long examId, CreateWoundExaminationDto createWoundExaminationDto){
        WoundExaminationDto dto = woundExaminationService.getWoundExaminationById(examId);
        WoundDto woundDto = toWoundDto(dto.getWound());
        dto.setNurseAssessment(createWoundExaminationDto.getNurseAssessment());
        woundExaminationService.saveWoundExamination(woundExaminationService.toWoundExamination(dto));
        woundRepository.save(toWound(woundDto));
        return getAllWoundExamDtosFromWound(woundDto.getId());
    }

    public List<WoundExaminationDto> getAllWoundExamDtosFromWound(Long woundId){
        WoundDto dto = getWoundById(woundId);
        List<WoundExaminationDto> dtos = new ArrayList<>();
        List<WoundExamination> woundExaminations = dto.getWoundExaminations();
        for (WoundExamination w : woundExaminations){
            dtos.add(woundExaminationService.toWoundExaminationDto(w));
        }return dtos;
    }



    public Set<WoundDto> getWoundsToAsses () {
        List<WoundExaminationDto> dtos = woundExaminationService.getAllWoundExamination();
        Set<WoundDto> woundsToAsses = new HashSet<>();
        for (WoundExaminationDto w : dtos) {
            if (w.getFile() != null && w.getNurseAssessment() == null) {
                woundsToAsses.add(toWoundDto(w.getWound()));
            }
        }
        return woundsToAsses;
    }

    public Wound saveWound(Wound wound){
        return woundRepository.save(wound);
    }



}
