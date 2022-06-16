package nl.novi.assigment.homecare.model.dto;

import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.Wound;

import java.util.List;

public class CreatePatientDto {

    private String name;
    private List<Wound> wounds;
    private String dateOfBirth;
    private String password;
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

    public List<Wound> getWounds() {
        return wounds;
    }

    public void setWounds(List<Wound> wounds) {
        this.wounds = wounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
