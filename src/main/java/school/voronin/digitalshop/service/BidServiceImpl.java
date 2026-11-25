package school.voronin.digitalshop.service;

import org.springframework.stereotype.Service;
import school.voronin.digitalshop.entity.BidEntity;
import school.voronin.digitalshop.entity.LotEntity;
import school.voronin.digitalshop.model.Bid;
import school.voronin.digitalshop.model.LotStatus;
import school.voronin.digitalshop.repository.BidRepository;
import school.voronin.digitalshop.repository.LotRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final LotRepository lotRepository;

    public BidServiceImpl(BidRepository bidRepository, LotRepository lotRepository){
        this.bidRepository = bidRepository;
        this.lotRepository = lotRepository;
    }

    @Override
    public List<Bid> getBidsByLotId(UUID lotId) {
        return bidRepository.findByLotId(lotId).stream().map(this::toDomain).toList();
    }

    @Override
    public Bid getTopBid(UUID lotId){
        return bidRepository.findTopByLotIdOrderByAmountDesc(lotId).
                map(this::toDomain).orElseThrow(() -> new NoSuchElementException("Ставок нет"));
    }

    @Override
    public synchronized Bid placeBid(UUID lotId, UUID buyerId, BigDecimal amount){
        LotEntity lot = lotRepository.findById(lotId).
                orElseThrow(() -> new NoSuchElementException("Лот не найден"));

        if (lot.getStatus() != LotStatus.ACTIVE){
            throw new IllegalStateException("Лот не активен");
        }

        if (LocalDateTime.now().isAfter(lot.endsAt)){
            throw new IllegalStateException("Время ставок вышло");
        }

        if (amount.compareTo(lot.getCurrentPrice()) <= 0){
            throw new IllegalArgumentException("Ставка должна быть выше: " + lot.getCurrentPrice());
        }

        lot.setCurrentPrice(amount);
        lotRepository.save(lot);

        BidEntity bid = new BidEntity();
        bid.setLotId(lot.getId());
        bid.setBuyerId(buyerId);
        bid.setAmount(amount);

        BidEntity saved = bidRepository.save(bid);
        return toDomain(saved);
    }

    private Bid toDomain(BidEntity entity) {
        return new Bid(
                entity.getId(),
                entity.getLotId(),
                entity.getBuyerId(),
                entity.getAmount(),
                entity.getCreatedAt()
        );
    }
}