package school.voronin.digitalshop.entity;

import jakarta.persistence.*;
import school.voronin.digitalshop.model.LotStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "lots")
public class LotEntity extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(name = "start_price", nullable = false)
    private BigDecimal startPrice;

    @Column(name = "current_price", nullable = false)
    private BigDecimal currentPrice;

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "winner_id")
    private UUID winnerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LotStatus status;

    @Column(name = "ends_at", nullable = false)
    public LocalDateTime endsAt;

    public LotEntity(){

    }

    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title = title; }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }
    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public UUID getSellerId() {
        return sellerId;
    }
    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public UUID getWinnerId() {
        return winnerId;
    }
    public void setWinnerId(UUID winnerId) {
        this.winnerId = winnerId;
    }

    public LotStatus getStatus() {
        return status;
    }
    public void setStatus(LotStatus status) {
        this.status = status;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }
    public void setEndsAt(LocalDateTime endsAt) {
        this.endsAt = endsAt;
    }
}
