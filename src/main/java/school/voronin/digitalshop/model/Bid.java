package school.voronin.digitalshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Bid (
        Long id,
        Long lotId,
        Long buyerId,
        BigDecimal amount,
        LocalDateTime createdAt

        ){
}
