package school.voronin.digitalshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.voronin.digitalshop.entity.LotEntity;
import school.voronin.digitalshop.model.LotStatus;

import java.util.List;
import java.util.UUID;

public interface LotRepository extends JpaRepository<LotEntity, UUID> {
    List<LotEntity> findByStatus(LotStatus status);
}