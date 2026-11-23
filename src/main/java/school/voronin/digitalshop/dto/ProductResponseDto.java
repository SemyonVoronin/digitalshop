package school.voronin.digitalshop.dto;

import school.voronin.digitalshop.model.ProductType;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDto (
        UUID id,
        String title,
        String description,
        BigDecimal price,
        ProductType type,
        Integer stock,
        Boolean available
){


}
