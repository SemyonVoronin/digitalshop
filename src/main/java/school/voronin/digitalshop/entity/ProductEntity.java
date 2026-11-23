package school.voronin.digitalshop.entity;

import jakarta.persistence.*;
import school.voronin.digitalshop.model.ProductType;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType type;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Boolean available;

    public ProductEntity(){

    }

    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title = title;}

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }
    public void setType(ProductType type) {
        this.type = type;
    }

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public boolean getAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
