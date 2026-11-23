package school.voronin.digitalshop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.voronin.digitalshop.model.ProductType;

import java.math.BigDecimal;

public record ProductRequestDto (
        @NotBlank(message = "Название не может быть пустым") String title,
        @NotBlank(message = "Описание не может быть пустым") String description,
        @NotNull(message = "Цена обязательна") @DecimalMin(value = "0.01") BigDecimal price,
        @NotNull(message = "Тип обязателен") ProductType type,
        @NotNull(message = "Количество обязательно") @Min(0) Integer stock,
        @NotNull Boolean available
        ){


}
