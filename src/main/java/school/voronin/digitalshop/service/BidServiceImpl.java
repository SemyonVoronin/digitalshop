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
    public List<Bid> getBidsByLotId(Long lotId) {
        UUID realUuid = lotRepository.findAll().stream()
                .filter(l -> l.getId() != null && l.getId().hashCode() == lotId.intValue())
                .map(LotEntity::getId)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Лот не найден: " + lotId));

        // Ищем ставки по UUID
        return bidRepository.findByLotId(realUuid).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Bid getTopBid(Long lotId){
        UUID uuid = new UUID(0, lotId);
        return bidRepository.findTopByLotIdOrderByAmountDesc(uuid).map(this::toDomain).
                orElseThrow(() -> new NoSuchElementException("Ставок нет"));
    }

    @Override
    public synchronized Bid placeBid(Long lotId, Long buyerId, BigDecimal amount){
        LotEntity lot = lotRepository.findAll().stream().
                filter(l -> l.getId() != null && l.getId().hashCode() == lotId.intValue()).
                findFirst().orElseThrow(() -> new NoSuchElementException("Лот не найден"));

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
                (long) entity.getId().hashCode(),
                (long) entity.getLotId().hashCode(),
                entity.getBuyerId(),
                entity.getAmount(),
                entity.getCreatedAt()
        );
    }
}