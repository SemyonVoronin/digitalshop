package school.voronin.digitalshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.voronin.digitalshop.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

}
