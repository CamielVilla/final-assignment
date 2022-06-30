package nl.novi.assigment.homecare.model.dto;

import nl.novi.assigment.homecare.model.entity.Wound;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public class CreatePatientDto {

    private String name;
    private Set<Wound> wounds;
    private String dateOfBirth;
    @NotNull
    @Size(min = 6)
    private String password;
    @Email
    @NotNull
    private String email;


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Wound> getWounds() {
        return wounds;
    }

    public void setWounds(Set<Wound> wounds) {
        this.wounds = wounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
