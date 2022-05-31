package nl.novi.assigment.homecare.domain.dto;

import nl.novi.assigment.homecare.domain.entity.Wound;

import java.time.LocalDateTime;
import java.util.Date;

public class WoundPhotoDto {

    private Long id;
    private String nurseAssessment;
    private LocalDateTime assessmentDate;
    private String patientComment;
    private LocalDateTime photoDate;
    private Wound wound;

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

    public LocalDateTime getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(LocalDateTime photoDate) {
        this.photoDate = photoDate;
    }

    public Wound getWound() {
        return wound;
    }

    public void setWound(Wound wound) {
        this.wound = wound;
    }
}
