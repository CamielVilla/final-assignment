package nl.novi.assigment.homecare.repository;

import nl.novi.assigment.homecare.domain.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository <Nurse, Long> {
}
