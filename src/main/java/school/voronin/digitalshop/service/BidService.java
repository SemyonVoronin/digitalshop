package school.voronin.digitalshop.service;

import school.voronin.digitalshop.model.Bid;

import java.math.BigDecimal;
import java.util.List;

public interface BidService {
    List<Bid> getBidsByLotId(Long id);
    Bid placeBid(Long id, Long buyerId, BigDecimal amount);
    Bid getTopBid(Long id);
}
