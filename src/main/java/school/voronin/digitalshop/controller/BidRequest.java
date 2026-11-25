package school.voronin.digitalshop.controller;

import java.math.BigDecimal;
import java.util.UUID;

public record BidRequest(
        UUID buyerId,
        BigDecimal amount
) {

}
