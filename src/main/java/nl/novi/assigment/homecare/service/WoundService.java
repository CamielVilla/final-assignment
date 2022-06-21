package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.Wound;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.repository.FileUploadRepository;
import nl.novi.assigment.homecare.repository.WoundExaminationRepository;
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


    public void addPhotoToWound(String name, Long woundId) {

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
        }
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




}
