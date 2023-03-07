package pl.pb.ogloszeniadrobne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pb.ogloszeniadrobne.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    List<User> findAllUsersByRoles_Name(String role);
    void deleteByEmail(String email);
}
