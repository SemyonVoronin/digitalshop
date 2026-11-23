package school.voronin.digitalshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.voronin.digitalshop.entity.ProductEntity;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

}
