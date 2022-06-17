package nl.novi.assigment.homecare.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class FileUploadResponse {

    @Id
    private String fileName;
    private String contentType;
    private String url;

    @OneToOne
    WoundExamination woundExamination;
//    private LocalDate photoDate;
//    private String patientComment;


    public FileUploadResponse(String fileName, String contentType, String url) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.url = url;
    }

    public WoundExamination getWoundExamination() {
        return woundExamination;
    }

    public void setWoundExamination(WoundExamination woundExamination) {
        this.woundExamination = woundExamination;
    }

    public FileUploadResponse() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
