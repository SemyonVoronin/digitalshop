package school.voronin.digitalshop.service;

import school.voronin.digitalshop.model.Bid;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BidService {
    List<Bid> getBidsByLotId(UUID id);
    Bid placeBid(UUID id, UUID buyerId, BigDecimal amount);
    Bid getTopBid(UUID id);
}
