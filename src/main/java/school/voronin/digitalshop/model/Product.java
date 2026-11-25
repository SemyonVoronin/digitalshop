package school.voronin.digitalshop.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Product (
        UUID id,
        String title,
        String description,
        BigDecimal price,
        ProductType type,
        Integer stock,
        Boolean available
){

}
