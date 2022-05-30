package nl.novi.assigment.homecare.service;

import nl.novi.assigment.homecare.domain.dto.CreatePatientDto;
import nl.novi.assigment.homecare.domain.dto.CreateWoundDto;
import nl.novi.assigment.homecare.domain.entity.Patient;
import nl.novi.assigment.homecare.domain.entity.Wound;
import nl.novi.assigment.homecare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(CreatePatientDto createPatientDto) {
        Patient patient = new Patient();
        patient.setName(createPatientDto.getName());
        patient.setDateOfBirth(createPatientDto.getDateOfBirth());
        patient.setPassword(createPatientDto.getPassword());
        return patientRepository.save(patient);
    }


    public Patient getPatientById(Long id){
        if (patientRepository.existsById(id)) {
            Patient patient = patientRepository.findById(id).get();
            return patient;
        }else{ throw new RuntimeException();
        }
    }
}
