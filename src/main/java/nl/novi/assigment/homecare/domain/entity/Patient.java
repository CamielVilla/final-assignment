package nl.novi.assigment.homecare.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Patient")
@DiscriminatorValue("patient")
public class Patient extends User {

    private String dateOfBirth;

    @JsonIgnore
    @OneToMany (mappedBy = "patient")
    private List<Wound> wounds;


    public List<Wound> getWounds() {
        return wounds;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setWounds(List<Wound> wounds) {
        this.wounds = wounds;
    }

}
