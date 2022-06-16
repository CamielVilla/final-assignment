package nl.novi.assigment.homecare.repository;

import nl.novi.assigment.homecare.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository <Patient, Long> {
}
