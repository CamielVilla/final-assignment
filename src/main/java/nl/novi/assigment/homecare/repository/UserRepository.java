package nl.novi.assigment.homecare.repository;

import nl.novi.assigment.homecare.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from users")
    List<User> findAllUser();
}
