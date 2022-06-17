package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
import nl.novi.assigment.homecare.model.entity.Wound;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.repository.WoundExaminationRepository;
import nl.novi.assigment.homecare.repository.WoundRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class WoundService {

    @Value("${my.upload_location}")
    private Path fileStoragePath;
    private final String fileStorageLocation;


    private final WoundRepository woundRepository;
    private final WoundExaminationRepository woundExaminationRepository;

//    public WoundService(WoundRepository woundRepository, WoundExaminationRepository woundExaminationRepository) {
//        this.woundRepository = woundRepository;
//        this.woundExaminationRepository = woundExaminationRepository;
//    }



    public WoundService(@Value("${my.upload_location}")
                        String fileStorageLocation,
                        WoundExaminationRepository woundExaminationRepository,
                        WoundRepository woundRepository
                        )
    {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.woundRepository = woundRepository;
        this.fileStorageLocation = fileStorageLocation;
        this.woundExaminationRepository = woundExaminationRepository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }

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
        woundDto.setWoundExamination(wound.getWoundExaminations());
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



//    public WoundDto addWoundExaminationToToWound(Long woundId, CreateWoundExaminationDto createWoundExaminationDto){
//        WoundDto woundDto = getWoundById(woundId);
//        List<WoundExamination> woundExaminations = woundDto.getWoundExamination();
//        WoundExamination woundExamination = new WoundExamination();
////        woundExamination.setPhotoDate(LocalDate.now());
//        woundExamination.setWound(toWound(woundDto));
//        woundExamination.setPatientComment(createWoundExaminationDto.getPatientComment());
////        woundExamination.setFile(createWoundExaminationDto.getFile());
//        woundExaminationRepository.save(woundExamination);
//        woundExaminations.add(woundExamination);
//        Wound savedWound = woundRepository.save(toWound(woundDto));
//        return (toWoundDto(savedWound));
//    }

//    public String storeFile(MultipartFile file, String url, Long woundId) {
//        WoundDto woundDto = getWoundById(woundId);
//        WoundExamination woundExamination = new WoundExamination();
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);
//        try {
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new RuntimeException("Issue in storing the file", e);
//        }
//
//       woundExaminationRepository.save(woundExamination);
//
////        woundExaminationRepository.save(new WoundExamination(fileName, file.getContentType(), url, date, toWound(woundDto)));
//        return fileName;
//    }




}
