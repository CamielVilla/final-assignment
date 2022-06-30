package nl.novi.assigment.homecare.model.dto;

import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.Wound;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WoundExaminationDto {

    private Long id;
    private String nurseAssessment;
    private LocalDateTime assessmentDate;
    private String patientComment;
    private LocalDate photoDate;
    private Wound wound;
    private FileUploadResponse file;

    public FileUploadResponse getFile() {
        return file;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNurseAssessment() {
        return nurseAssessment;
    }

    public void setNurseAssessment(String nurseAssessment) {
        this.nurseAssessment = nurseAssessment;
    }

    public LocalDateTime getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(LocalDateTime assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getPatientComment() {
        return patientComment;
    }

    public void setPatientComment(String patientComment) {
        this.patientComment = patientComment;
    }

    public LocalDate getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(LocalDate photoDate) {
        this.photoDate = photoDate;
    }

    public Wound getWound() {
        return wound;
    }

    public void setWound(Wound wound) {
        this.wound = wound;
    }
}
