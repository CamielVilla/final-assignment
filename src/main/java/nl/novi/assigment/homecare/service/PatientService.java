package nl.novi.assigment.homecare.service;

import nl.novi.assigment.homecare.domain.dto.CreatePatientDto;
import nl.novi.assigment.homecare.domain.dto.PatientDto;
import nl.novi.assigment.homecare.domain.entity.Patient;
import nl.novi.assigment.homecare.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientDto addPatient(CreatePatientDto createPatientDto) {
        Patient patient = new Patient();
        patient.setName(createPatientDto.getName());
        patient.setDateOfBirth(createPatientDto.getDateOfBirth());
        patient.setPassword(createPatientDto.getPassword());
        patient.setEmail(createPatientDto.getEmail());
        patient.setRole("PATIENT");
        patient.setEnabled(1);
        Patient savedPatient = patientRepository.save(patient);
        return toPatientDto(savedPatient);
    }
    public PatientDto toPatientDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setEmail(patient.getEmail());
        patientDto.setDateOfBirth(patient.getDateOfBirth());
        patientDto.setWounds(patient.getWounds());
        patientDto.setRole(patient.getRole());
        patientDto.setEnabled(patient.getEnabled());
        return patientDto;
    }

    public Patient getPatientById(Long id){
        if (patientRepository.existsById(id)) {
            Patient patient = patientRepository.findById(id).get();
            return patient;
        }else{ throw new RuntimeException();
        }
    }
}
