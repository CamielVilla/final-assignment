//package nl.novi.assigment.homecare.controller;
//
//import nl.novi.assigment.homecare.model.dto.CreateWoundExaminationDto;
//import nl.novi.assigment.homecare.model.dto.WoundExaminationDto;
//import nl.novi.assigment.homecare.model.entity.WoundExamination;
//import nl.novi.assigment.homecare.service.WoundExaminationService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("woundexamination")
//public class WoundExaminationController {
//
//    private final WoundExaminationService woundExaminationService;
//    private final PhotoController photoController;
//
//
//    public WoundExaminationController(WoundExaminationService woundExaminationService, PhotoController photoController) {
//        this.woundExaminationService = woundExaminationService;
//        this.photoController = photoController;
//    }
//
////    @PostMapping
////    public ResponseEntity<WoundExaminationDto> addWoundExamination(@RequestBody CreateWoundExaminationDto createWoundExaminationDto){
////        final WoundExaminationDto woundExaminationDto = woundExaminationService.addWoundExamination(createWoundExaminationDto);
////        final URI location = URI.create("woundexamination");
////        return ResponseEntity
////                .created(location)
////                .body(woundExaminationDto);
////    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<WoundExaminationDto> reviewWoundPhoto (@RequestBody CreateWoundExaminationDto createWoundExaminationDto, @PathVariable Long id) {
//        final WoundExaminationDto woundExaminationDto = woundExaminationService.reviewWoundPhoto(createWoundExaminationDto, id);
//        return ResponseEntity.ok(woundExaminationDto);
//    }
//
////
////    @PostMapping("/{id}/photo")
////    public void assignPhotoToWoundPhoto(@PathVariable Long id, @RequestBody MultipartFile file){
////
////        WoundExamination photo = photoController.singleFileUpload(file);
////
////        woundExaminationService.assignPhotoToWoundExamination(photo.getFileName(), id);
////    }
//
//}
