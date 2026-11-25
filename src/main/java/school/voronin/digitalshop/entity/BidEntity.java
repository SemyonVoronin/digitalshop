package school.voronin.digitalshop.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "bids")
public class BidEntity extends BaseEntity{

    @Column(name = "lot_id", nullable = false)
    private UUID lotId;

    @Column(name = "buyer_id", nullable = false)
    private UUID buyerId;

    @Column(nullable = false)
    private BigDecimal amount;

    public BidEntity(){

    }

    public UUID getLotId(){ return lotId;}
    public void setLotId(UUID lotId){ this.lotId = lotId;}

    public UUID getBuyerId(){ return buyerId; }
    public void setBuyerId(UUID buyerId){ this.buyerId = buyerId; }

    public BigDecimal getAmount(){ return amount; }
    public void setAmount(BigDecimal amount){ this.amount = amount; }




}
