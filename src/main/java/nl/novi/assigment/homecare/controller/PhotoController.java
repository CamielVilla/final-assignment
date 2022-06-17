//package nl.novi.assigment.homecare.controller;
//
//import nl.novi.assigment.homecare.model.entity.WoundExamination;
//
//import nl.novi.assigment.homecare.service.WoundService;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Objects;
//@RestController
//@CrossOrigin
//public class PhotoController {
//    private final WoundService woundService;
//
//    public PhotoController(WoundService woundService) {
//        this.woundService = woundService;
//    }
//
//    @PostMapping("/upload")
//    WoundExamination singleFileUpload(@RequestParam("file") MultipartFile file){
//
//        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
//
//        String contentType = file.getContentType();
//
//        LocalDate date = LocalDate.now();
//
//        String fileName = photoService.storeFile(file, url);
//
//
//
//        return new WoundExamination(fileName, contentType, url, date );
//    }
//
//    @GetMapping("/download/{fileName}")
//    ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {
//
//        Resource resource = photoService.downLoadFile(fileName);
//        String mimeType;
//
//        try{
//            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException e) {
//            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
//        }
//
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
//    }
//}