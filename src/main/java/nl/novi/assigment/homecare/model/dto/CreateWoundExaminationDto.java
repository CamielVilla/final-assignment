package nl.novi.assigment.homecare.model.dto;

import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.model.entity.Wound;
import java.time.LocalDateTime;
import java.util.Date;

public class CreateWoundExaminationDto {

    private String nurseAssessment;
    private Date assessmentDate;
    private String patientComment;
    private LocalDateTime photoDate;
    private Wound wound;
    private WoundExamination file;

    public WoundExamination getFile() {
        return file;
    }

    public void setFile(WoundExamination file) {
        this.file = file;
    }

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
