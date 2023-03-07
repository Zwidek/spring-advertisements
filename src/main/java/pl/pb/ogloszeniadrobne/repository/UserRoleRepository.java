package pl.pb.ogloszeniadrobne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pb.ogloszeniadrobne.model.UserRole;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
}
