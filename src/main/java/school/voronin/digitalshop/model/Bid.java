package school.voronin.digitalshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Bid (
        UUID id,
        UUID lotId,
        UUID buyerId,
        BigDecimal amount,
        LocalDateTime createdAt
        ){
}
