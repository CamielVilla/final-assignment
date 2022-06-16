package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.model.dto.CreateWoundPhotoDto;
import nl.novi.assigment.homecare.model.dto.WoundPhotoDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.WoundPhoto;
import nl.novi.assigment.homecare.repository.FileUploadRepository;
import nl.novi.assigment.homecare.repository.WoundPhotoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WoundPhotoService {
    private final WoundPhotoRepository woundPhotoRepository;
    private final WoundService woundService;
    private final FileUploadRepository fileUploadRepository;

    public WoundPhotoService(WoundPhotoRepository woundPhotoRepository, WoundService woundService, FileUploadRepository fileUploadRepository) {
        this.woundPhotoRepository = woundPhotoRepository;
        this.woundService = woundService;
        this.fileUploadRepository = fileUploadRepository;
    }


    public WoundPhotoDto addWoundPhoto(CreateWoundPhotoDto createWoundPhotoDto) {
        WoundPhoto woundPhoto = new WoundPhoto();
        woundPhoto.setPhotoDate(LocalDateTime.now());
        woundPhoto.setPatientComment(createWoundPhotoDto.getPatientComment());
        woundPhoto.setWound(woundService.toWound(woundService.getWoundById(createWoundPhotoDto.getWoundId())));
        woundPhotoRepository.save(woundPhoto);
        return toWoundPhotoDto(woundPhoto);
    }

    public WoundPhotoDto toWoundPhotoDto(WoundPhoto woundPhoto) {
        WoundPhotoDto woundPhotoDto = new WoundPhotoDto();
        woundPhotoDto.setId(woundPhoto.getId());
        woundPhotoDto.setNurseAssessment(woundPhoto.getNurseAssessment());
        woundPhotoDto.setAssessmentDate(woundPhoto.getAssessmentDate());
        woundPhotoDto.setPatientComment(woundPhoto.getPatientComment());
        woundPhotoDto.setPhotoDate(woundPhoto.getPhotoDate());
        woundPhotoDto.setWound(woundPhoto.getWound());
        return woundPhotoDto;
    }

    public WoundPhoto toWoundPhoto(WoundPhotoDto woundPhotoDto){
        WoundPhoto woundPhoto = new WoundPhoto();
        woundPhoto.setId(woundPhotoDto.getId());
        woundPhoto.setNurseAssessment(woundPhotoDto.getNurseAssessment());
        woundPhoto.setAssessmentDate(woundPhotoDto.getAssessmentDate());
        woundPhoto.setPatientComment(woundPhotoDto.getPatientComment());
        woundPhoto.setPhotoDate(woundPhotoDto.getPhotoDate());
        woundPhoto.setWound(woundPhotoDto.getWound());
        return woundPhoto;
    }

    public WoundPhotoDto getWoundPhotoDtoById(Long id){
        if (woundPhotoRepository.existsById(id)){
            WoundPhotoDto woundPhotoDto = toWoundPhotoDto(woundPhotoRepository.findById(id).get());
            return woundPhotoDto;
        }else{
            throw new RuntimeException();
        }
    }


    public WoundPhotoDto reviewWoundPhoto(CreateWoundPhotoDto createWoundPhotoDto, Long id) {
        WoundPhoto woundPhoto = toWoundPhoto(getWoundPhotoDtoById(id));
        woundPhoto.setNurseAssessment(createWoundPhotoDto.getNurseAssessment());
        woundPhoto.setAssessmentDate(LocalDateTime.now());
        woundPhotoRepository.save(woundPhoto);
        return toWoundPhotoDto(woundPhoto);
    }

    public void assignPhotoToWoundPhoto(String name, Long id) {

        WoundPhoto woundPhoto = toWoundPhoto(getWoundPhotoDtoById(id));
        Optional<FileUploadResponse> optionalFileUploadResponse = fileUploadRepository.findByFileName(name);

        if (optionalFileUploadResponse.isPresent()) {
            FileUploadResponse photo = optionalFileUploadResponse.get();
            woundPhoto.setFile(photo);
            woundPhotoRepository.save(woundPhoto);
        }
    }
}
