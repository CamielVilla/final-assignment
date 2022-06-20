package nl.novi.assigment.homecare.service;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.Wound;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.repository.FileUploadRepository;
import nl.novi.assigment.homecare.repository.WoundExaminationRepository;
import nl.novi.assigment.homecare.repository.WoundRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WoundService {

//    @Value("${my.upload_location}")
//    private Path fileStoragePath;
//    private final String fileStorageLocation;


    private final WoundRepository woundRepository;
    private final WoundExaminationRepository woundExaminationRepository;
    private final FileUploadRepository fileUploadRepository;

    public WoundService(WoundRepository woundRepository, WoundExaminationRepository woundExaminationRepository, FileUploadRepository fileUploadRepository) {
        this.woundRepository = woundRepository;
        this.woundExaminationRepository = woundExaminationRepository;
        this.fileUploadRepository = fileUploadRepository;
    }


    //    public WoundService(WoundRepository woundRepository, WoundExaminationRepository woundExaminationRepository) {
//        this.woundRepository = woundRepository;
//        this.woundExaminationRepository = woundExaminationRepository;
//    }



//    public WoundService(@Value("${my.upload_location}")
//                        String fileStorageLocation,
//                        WoundExaminationRepository woundExaminationRepository,
//                        WoundRepository woundRepository
//                        )
//    {
//        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
//        this.woundRepository = woundRepository;
//        this.fileStorageLocation = fileStorageLocation;
//        this.woundExaminationRepository = woundExaminationRepository;
//
//        try {
//            Files.createDirectories(fileStoragePath);
//        } catch (IOException e) {
//            throw new RuntimeException("Issue in creating file directory");
//        }
//
//    }

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

public WoundExamination addWoundExamination(Long woundId, CreateWoundExaminationDto dto){
        WoundDto woundDto = getWoundById(woundId);
        WoundExamination woundExamination = new WoundExamination();
        woundExamination.setWound(toWound(woundDto));
        woundExamination.setNurseAssessment(dto.getNurseAssessment());
        woundExaminationRepository.save(woundExamination);

//    List<WoundExamination> woundExaminations = woundDto.getWoundExaminations();
//
//    woundExaminations.add(woundExamination);
//    woundDto.setWoundExaminations(woundExaminations);
//    woundRepository.save(toWound(woundDto));
    return woundExamination;
}

    public void addPhotoToWound(String name, Long woundId) {


        Optional<FileUploadResponse> optionalFileUploadResponse = fileUploadRepository.findByFileName(name);

        if (woundRepository.existsById(woundId) && optionalFileUploadResponse.isPresent()) {
            WoundDto woundDto = getWoundById(woundId);

            List<WoundExamination> woundExaminations = woundDto.getWoundExaminations();


            WoundExamination woundExamination = new WoundExamination();
            woundExamination.setWound(toWound(woundDto));

            FileUploadResponse photo = optionalFileUploadResponse.get();

            woundExamination.setFile(photo);

            woundExaminationRepository.save(woundExamination);
            woundExaminations.add(woundExamination);
            woundDto.setWoundExaminations(woundExaminations);
            woundRepository.save(toWound(woundDto));
        }
    }
}
