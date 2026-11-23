package school.voronin.digitalshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.voronin.digitalshop.entity.BidEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BidRepository extends JpaRepository<BidEntity, UUID> {
    List<BidEntity> findByLotId(UUID id);
    Optional<BidEntity> findTopByLotIdOrderByAmountDesc(UUID id);
}
