package school.voronin.digitalshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Lot (
        UUID id,
        String title,
        String description,
        BigDecimal startPrice,
        BigDecimal currentPrice,
        UUID sellerId,
        UUID winnerId,
        LotStatus status,
        LocalDateTime createdAt,
        LocalDateTime endsAt
){
}
