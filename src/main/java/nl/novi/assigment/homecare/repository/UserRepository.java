package nl.novi.assigment.homecare.repository;

import nl.novi.assigment.homecare.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
