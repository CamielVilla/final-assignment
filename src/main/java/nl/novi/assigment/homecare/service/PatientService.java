package nl.novi.assigment.homecare.service;

import nl.novi.assigment.homecare.model.dto.*;
import nl.novi.assigment.homecare.model.entity.FileUploadResponse;
import nl.novi.assigment.homecare.model.entity.Patient;
import nl.novi.assigment.homecare.model.entity.Wound;
import nl.novi.assigment.homecare.model.entity.WoundExamination;
import nl.novi.assigment.homecare.repository.FileUploadRepository;
import nl.novi.assigment.homecare.repository.PatientRepository;
import nl.novi.assigment.homecare.repository.WoundExaminationRepository;
import nl.novi.assigment.homecare.repository.WoundRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService {
    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
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
        patientDto.setPassword(patient.getPassword());
        return patientDto;
    }

    public Patient toPatient(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setEmail(patientDto.getEmail());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setWounds(patientDto.getWounds());
        patient.setRole(patientDto.getRole());
        patient.setEnabled(patientDto.getEnabled());
        return patient;
    }


    public PatientDto getPatientById(Long id){
        if (patientRepository.existsById(id)) {
            PatientDto patientDto = (toPatientDto(patientRepository.findById(id).get()));
            return patientDto;
        }else{ throw new RuntimeException();
        }
    }


    public Set<Wound> getAllWoundsFromPatient(Long id){
        Set<Wound> wounds = getPatientById(id).getWounds();
        return wounds;
    }

    public PatientDto updatePatient(Long id, CreatePatientDto createPatientDto){
        if (patientRepository.existsById(id)){
            Patient oldPatient = patientRepository.findById(id).get();

            if (createPatientDto.getPassword() != null){
                oldPatient.setPassword(createPatientDto.getPassword());
            }
            Patient savedPatient = patientRepository.save(oldPatient);
            return toPatientDto(savedPatient);
        }else{
            throw new RuntimeException();
        }
    }

    public Patient savePatient(Patient patient){
        return patientRepository.save(patient);
    }

    public List<PatientDto> getAllPatients(){
        final List<Patient> patientList = patientRepository.findAll();
        List<PatientDto> dtoList = new ArrayList<>();
        for(Patient patient : patientList){
            PatientDto patientDto = toPatientDto(patient);
            dtoList.add(patientDto);
        }
        return dtoList;
    }

//
//    public void addExamToWound(Long patientId, Long woundId, CreateWoundExaminationDto dto) {
//        if (patientRepository.existsById(patientId)) {
//            PatientDto patientDto = getPatientById(patientId);
//            Set<Wound> wounds = patientDto.getWounds();
//            for (Wound w : wounds) {
//                if (w.getId().equals(woundId)) {
//                    WoundExamination woundExamination = new WoundExamination();
//                    woundExamination.setNurseAssessment(dto.getNurseAssessment());
//                    woundExamination.setWound(w);
//                    WoundExamination savedWoundExam = woundExaminationRepository
//                            .save(woundExamination);
//                    List<WoundExamination> woundExaminations = w.getWoundExaminations();
//                    woundExaminations.add(savedWoundExam);
//                    w.setWoundExaminations(woundExaminations);
//                    woundRepository.save(w);
//                } else {
//                    throw new RuntimeException();
//                }
//            }
//        }
//    }
}
