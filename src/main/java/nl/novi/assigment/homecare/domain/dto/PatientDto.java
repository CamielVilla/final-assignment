package nl.novi.assigment.homecare.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.novi.assigment.homecare.domain.entity.Wound;

import java.util.List;

public class PatientDto {

    private Long id;
    private String name;
    private String dateOfBirth;
    private String email;
    private String role;
    private int enabled;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    private List<Wound> wounds;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
