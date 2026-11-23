package school.voronin.digitalshop.model;

public record CartItem (
        Product product,
        Integer quantity
) {
    public double getTotalPrice(){
        return product.price().doubleValue() * quantity;
    }
}
