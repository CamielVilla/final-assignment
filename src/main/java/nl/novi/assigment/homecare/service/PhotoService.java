//package nl.novi.assigment.homecare.service;
//
//import nl.novi.assigment.homecare.model.entity.WoundExamination;
//import nl.novi.assigment.homecare.repository.FileUploadRepository;
//import nl.novi.assigment.homecare.repository.WoundExaminationRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//@Service
//public class PhotoService {
//    @Value("${my.upload_location}")
//    private Path fileStoragePath;
//    private final String fileStorageLocation;
//
//    private final WoundExaminationRepository woundExaminationRepository;
//
//    public PhotoService(@Value("${my.upload_location}") String fileStorageLocation, WoundExaminationRepository woundExaminationRepository) {
//        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
//
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
//
//    public String storeFile(MultipartFile file, String url) {
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//
//        LocalDate date = LocalDate.now();
//
//        Path filePath = Paths.get(fileStoragePath + "/" + fileName);
//        try {
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new RuntimeException("Issue in storing the file", e);
//        }
//        woundExaminationRepository.save(new WoundExamination(fileName, file.getContentType(), url, date));
//        return fileName;
//    }
//
//    public Resource downLoadFile(String fileName) {
//        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);
//        Resource resource;
//        try {
//            resource = new UrlResource(path.toUri());
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Issue in reading the file", e);
//        }
//        if(resource.exists()&& resource.isReadable()) {
//            return resource;
//        } else {
//            throw new RuntimeException("the file doesn't exist or not readable");
//        }
//    }
//
//}