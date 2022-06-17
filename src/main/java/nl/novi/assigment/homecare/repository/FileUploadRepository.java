package nl.novi.assigment.homecare.repository;

import nl.novi.assigment.homecare.model.entity.WoundExamination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<WoundExamination, String> {
    Optional<WoundExamination> findByFileName(String fileName);
}