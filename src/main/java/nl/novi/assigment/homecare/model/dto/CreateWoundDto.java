package nl.novi.assigment.homecare.model.dto;

import nl.novi.assigment.homecare.model.entity.Patient;
import nl.novi.assigment.homecare.model.entity.WoundExamination;

import javax.validation.constraints.NotNull;
import java.util.List;


public class CreateWoundDto {
    @NotNull
    private String treatmentPlan;
    @NotNull
    private String woundName;
    @NotNull
    private String woundLocation;
    private Patient patient;
    private List<WoundExamination> woundExamination;

    public List<WoundExamination> getWoundExamination() {
        return woundExamination;
    }

    public void setWoundExamination(List<WoundExamination> woundExamination) {
        this.woundExamination = woundExamination;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getWoundName() {
        return woundName;
    }

    public void setWoundName(String woundName) {
        this.woundName = woundName;
    }

    public String getWoundLocation() {
        return woundLocation;
    }

    public void setWoundLocation(String woundLocation) {
        this.woundLocation = woundLocation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
