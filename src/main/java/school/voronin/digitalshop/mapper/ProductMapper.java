package school.voronin.digitalshop.mapper;

import org.springframework.stereotype.Component;
import school.voronin.digitalshop.dto.ProductRequestDto;
import school.voronin.digitalshop.dto.ProductResponseDto;
import school.voronin.digitalshop.entity.ProductEntity;

@Component
public class ProductMapper {

    public ProductEntity toEntity(ProductRequestDto dto){
        ProductEntity entity = new ProductEntity();
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setType(dto.type());
        entity.setStock(dto.stock());
        entity.setAvailable(dto.available());

        return entity;
    }

    public ProductResponseDto toResponseDto(ProductEntity entity){
        return new ProductResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getType(),
                entity.getStock(),
                entity.getAvailable()
        );
    }
}
