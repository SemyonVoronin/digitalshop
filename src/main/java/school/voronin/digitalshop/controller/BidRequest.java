package school.voronin.digitalshop.controller;

import java.math.BigDecimal;

public record BidRequest(
        Long buyerId,
        BigDecimal amount
) {

}
