package school.voronin.digitalshop.service;

import school.voronin.digitalshop.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    void decreaseStock(Long productId, int quantity);

}
