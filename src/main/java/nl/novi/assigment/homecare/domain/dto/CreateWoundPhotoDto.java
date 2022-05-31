package nl.novi.assigment.homecare.domain.dto;

import nl.novi.assigment.homecare.domain.entity.Wound;


import java.util.Date;

public class CreateWoundPhotoDto {

    private String nurseAssessment;
    private Date assessmentDate;
    private String patientComment;
    private Date photoDate;
    private Wound wound;
    private Long woundId;


    public String getNurseAssessment() {
        return nurseAssessment;
    }

    public void setNurseAssessment(String nurseAssessment) {
        this.nurseAssessment = nurseAssessment;
    }

    public Date getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(Date assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getPatientComment() {
        return patientComment;
    }

    public void setPatientComment(String patientComment) {
        this.patientComment = patientComment;
    }

    public Date getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(Date photoDate) {
        this.photoDate = photoDate;
    }

    public Wound getWound() {
        return wound;
    }

    public void setWound(Wound wound) {
        this.wound = wound;
    }

    public Long getWoundId() {
        return woundId;
    }

    public void setWoundId(Long woundId) {
        this.woundId = woundId;
    }
}
