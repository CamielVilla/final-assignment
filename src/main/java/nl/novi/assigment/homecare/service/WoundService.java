package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.model.dto.CreateWoundPhotoDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.dto.WoundPhotoDto;
import nl.novi.assigment.homecare.model.entity.Wound;
import nl.novi.assigment.homecare.model.entity.WoundPhoto;
import nl.novi.assigment.homecare.repository.WoundPhotoRepository;
import nl.novi.assigment.homecare.repository.WoundRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WoundService {
    private final WoundRepository woundRepository;
    private final WoundPhotoRepository woundPhotoRepository;

    public WoundService(WoundRepository woundRepository, WoundPhotoRepository woundPhotoRepository) {
        this.woundRepository = woundRepository;
        this.woundPhotoRepository = woundPhotoRepository;
    }

    public WoundDto getWoundById (Long id){
        WoundDto woundDto = toWoundDto(woundRepository.findById(id).get());
        return woundDto;
    }

//    public WoundDto addWound(CreateWoundDto createWoundDto) {
//        Wound wound = new Wound();
//        wound.setWoundName(createWoundDto.getWoundName());
//        wound.setWoundLocation(createWoundDto.getWoundLocation());
//        wound.setTreatmentPlan(createWoundDto.getTreatmentPlan());
//        wound.setPatient(patientService.toPatient(patientService.getPatientById(createWoundDto.getPatientId())));
//        wound.setWoundPhotos(createWoundDto.getWoundPhotos());
//        Wound savedWound = woundRepository.save(wound);
//        return toWoundDto(savedWound);
//    }
    public WoundDto toWoundDto (Wound wound){
        WoundDto woundDto = new WoundDto();
        woundDto.setId(wound.getId());
        woundDto.setWoundName(wound.getWoundName());
        woundDto.setWoundLocation(wound.getWoundLocation());
        woundDto.setTreatmentPlan(wound.getTreatmentPlan());
        woundDto.setPatient(wound.getPatient());
        woundDto.setWoundPhotos(wound.getWoundPhotos());
        return woundDto;
    }

    public Wound toWound(WoundDto woundDto){
        Wound wound = new Wound();
        wound.setId(woundDto.getId());
        wound.setWoundName(woundDto.getWoundName());
        wound.setWoundLocation(woundDto.getWoundLocation());
        wound.setTreatmentPlan(woundDto.getTreatmentPlan());
        wound.setPatient(woundDto.getPatient());
        return wound;
    }

    public WoundDto addWoundPhotoToWound(Long woundId, CreateWoundPhotoDto createWoundPhotoDto){
        WoundDto woundDto = getWoundById(woundId);
        List<WoundPhoto> woundPhotos = woundDto.getWoundPhotos();
        WoundPhoto woundPhoto = new WoundPhoto();
        woundPhoto.setPhotoDate(LocalDateTime.now());
        woundPhoto.setWound(toWound(woundDto));
        woundPhoto.setPatientComment(createWoundPhotoDto.getPatientComment());
        woundPhoto.setFile(createWoundPhotoDto.getFile());
        woundPhotoRepository.save(woundPhoto);
        woundPhotos.add(woundPhoto);
        Wound savedWound = woundRepository.save(toWound(woundDto));
        return (toWoundDto(savedWound));
    }


}
