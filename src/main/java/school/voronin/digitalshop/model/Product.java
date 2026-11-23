package school.voronin.digitalshop.model;

import java.math.BigDecimal;

public record Product (
        Long id,
        String title,
        String description,
        BigDecimal price,
        ProductType type,
        Integer stock,
        Boolean available
){

}
