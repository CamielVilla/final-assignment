package nl.novi.assigment.homecare.repository;

import nl.novi.assigment.homecare.model.entity.Patient;
import nl.novi.assigment.homecare.model.entity.Wound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WoundRepository extends JpaRepository <Wound, Long>{
}
