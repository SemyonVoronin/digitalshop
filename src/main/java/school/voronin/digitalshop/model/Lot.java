package school.voronin.digitalshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Lot (
        Long id,
        String title,
        String description,
        BigDecimal startPrice,
        BigDecimal currentPrice,
        Long sellerId,
        Long winnerId,
        LotStatus status,
        LocalDateTime createdAt,
        LocalDateTime endsAt
){
}
