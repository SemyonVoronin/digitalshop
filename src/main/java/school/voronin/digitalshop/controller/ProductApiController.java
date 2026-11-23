package school.voronin.digitalshop.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.voronin.digitalshop.dto.ProductRequestDto;
import school.voronin.digitalshop.dto.ProductResponseDto;
import school.voronin.digitalshop.entity.ProductEntity;
import school.voronin.digitalshop.mapper.ProductMapper;
import school.voronin.digitalshop.repository.ProductRepository;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductApiController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductApiController(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<ProductResponseDto> getAll(){
        return productRepository.findAll().stream().map(productMapper::toResponseDto).toList();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable UUID id){
        ProductEntity entity = productRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Такого товара нет: " + id));
        return productMapper.toResponseDto(entity);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto dto){
        ProductEntity saved = productRepository.save(productMapper.toEntity(dto));

        return ResponseEntity.created(URI.create("/api/v1/products/" + saved.getId())).
                body(productMapper.toResponseDto(saved));

    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable UUID id, @RequestBody ProductRequestDto dto){

        ProductEntity entity = productRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Такого товара нет: " + id));

        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setType(dto.type());
        entity.setStock(dto.stock());
        entity.setAvailable(dto.available());

        ProductEntity saved = productRepository.save(entity);
        return productMapper.toResponseDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        if (!productRepository.existsById(id)){
            throw new RuntimeException("Такого товара нет");
        }
        productRepository.deleteById(id);
    }
}
