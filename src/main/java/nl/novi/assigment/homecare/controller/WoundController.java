package nl.novi.assigment.homecare.controller;


import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
import nl.novi.assigment.homecare.model.dto.WoundDto;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.service.WoundService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("wounds")
public class WoundController {

    private final WoundService woundService;
    public WoundController(WoundService woundService) {
        this.woundService = woundService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<WoundDto> getWoundById(@PathVariable Long id){
       return ResponseEntity.ok(woundService.getWoundById(id));
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<WoundDto> addWoundPhoto (@RequestBody CreateWoundExaminationDto createWoundExaminationDto, @PathVariable Long id){
        WoundDto woundDto = woundService.getWoundById(id);
        woundService.addWoundExaminationToToWound(woundDto.getId(), createWoundExaminationDto);
        return ResponseEntity.ok(woundDto);

    }

    @PostMapping("/upload")
    WoundExamination singleFileUpload(@RequestParam("file") MultipartFile file){

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        LocalDate date = LocalDate.now();

        String fileName = woundService.storeFile(file, url);



        return new WoundExamination(fileName, contentType, url, date );
    }

    @GetMapping("/download/{fileName}")
    ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = woundService.downLoadFile(fileName);
        String mimeType;

        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }
}



