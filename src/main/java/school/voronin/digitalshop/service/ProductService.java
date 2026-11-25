package school.voronin.digitalshop.service;

import school.voronin.digitalshop.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(UUID id);
    Product createProduct(Product product);
    Product updateProduct(UUID id, Product product);
    void deleteProduct(UUID id);
    void decreaseStock(UUID productId, int quantity);

}
