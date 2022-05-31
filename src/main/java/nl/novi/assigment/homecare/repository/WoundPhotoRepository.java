package nl.novi.assigment.homecare.repository;

import nl.novi.assigment.homecare.domain.entity.WoundPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WoundPhotoRepository extends JpaRepository <WoundPhoto, Long> {
}
