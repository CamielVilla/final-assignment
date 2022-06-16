package nl.novi.assigment.homecare.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Wound {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String treatmentPlan;
    private String woundName;
    private String woundLocation;

    @ManyToOne
    @JsonIgnore
    @JoinColumn (name = "patient_id")
    private Patient patient;

    @OneToMany (mappedBy = "wound")

    private List<WoundPhoto> woundPhotos;


    public List<WoundPhoto> getWoundPhotos() {
        return woundPhotos;
    }

    public void setWoundPhotos(List<WoundPhoto> woundPhotos) {
        this.woundPhotos = woundPhotos;
    }

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
