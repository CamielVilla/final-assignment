package nl.novi.assigment.homecare.domain.dto;

import nl.novi.assigment.homecare.domain.entity.Patient;

public class WoundDto {
    private Long id;
    private String treatmentPlan;
    private String woundName;
    private String woundLocation;

    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
