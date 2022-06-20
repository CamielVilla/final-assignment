package nl.novi.assigment.homecare.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "patient")
@DiscriminatorValue("patient")
public class Patient extends User {

    private String dateOfBirth;


    @OneToMany ( mappedBy = "patient", fetch = FetchType.EAGER)
    private Set<Wound> wounds;

    public Set<Wound> getWounds() {
        return wounds;
    }

    public void setWounds(Set<Wound> wounds) {
        this.wounds = wounds;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}
