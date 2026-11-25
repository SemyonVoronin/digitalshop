package school.voronin.digitalshop.service;


import org.springframework.stereotype.Service;
import school.voronin.digitalshop.entity.ProductEntity;
import school.voronin.digitalshop.model.Product;
import school.voronin.digitalshop.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Product getProductById(UUID id){
        ProductEntity entity = productRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Товар не найден:" + id));
        return toDomain(entity);
    }

    @Override
    public Product createProduct(Product product){
        ProductEntity entity = new ProductEntity();
        entity.setTitle(product.title());
        entity.setDescription(product.description());
        entity.setPrice(product.price());
        entity.setType(product.type());
        entity.setStock(product.stock());
        entity.setAvailable(product.available());

        ProductEntity saved = productRepository.save(entity);
        return toDomain(saved);

    }

    @Override
    public Product updateProduct(UUID id, Product product){
        ProductEntity entity = productRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Товар не найден: " + id));


        entity.setTitle(product.title());
        entity.setDescription(product.description());
        entity.setPrice(product.price());
        entity.setType(product.type());
        entity.setStock(product.stock());
        entity.setAvailable(product.available());

        ProductEntity saved = productRepository.save(entity);
        return toDomain(saved);

    }

    @Override
    public void deleteProduct(UUID id){
        ProductEntity entity = productRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Товаh не найден: " + id));

        productRepository.delete(entity);

    }

    @Override
    public void decreaseStock(UUID productId, int quantity){
        ProductEntity entity = productRepository.findById(productId).
                orElseThrow(() -> new NoSuchElementException("Товар не найден: " + productId));

        if (entity.getStock() < quantity){
            throw new IllegalArgumentException("Товаров нет в наличии: " + entity.getStock());
        }

        entity.setStock(entity.getStock() - quantity);
        productRepository.save(entity);
    }

    private Product toDomain(ProductEntity entity) {
        return new Product(
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
