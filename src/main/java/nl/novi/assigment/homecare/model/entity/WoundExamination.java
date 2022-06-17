package nl.novi.assigment.homecare.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
    public class WoundExamination {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nurseAssessment;
        private LocalDateTime assessmentDate;

        private String fileName;
        private String contentType;
        private String url;
        private LocalDate photoDate;
        private String patientComment;

    @ManyToOne
    @JsonIgnore
    @JoinColumn (name = "wound_id")
    private Wound wound;

        public WoundExamination(String fileName, String contentType, String url, LocalDate photoDate) {
            this.fileName = fileName;
            this.contentType = contentType;
            this.url = url;
            this.photoDate = photoDate;
        }

    public WoundExamination() {
    }

    public Wound getWound() {
        return wound;
    }

    public void setWound(Wound wound) {
        this.wound = wound;
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

    public LocalDate getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(LocalDate photoDate) {
        this.photoDate = photoDate;
    }

    public String getPatientComment() {
        return patientComment;
    }

    public void setPatientComment(String patientComment) {
        this.patientComment = patientComment;
    }



        public String getFileName() {
            return fileName;
        }

        public String getContentType() {
            return contentType;
        }

        public String getUrl() {
            return url;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

