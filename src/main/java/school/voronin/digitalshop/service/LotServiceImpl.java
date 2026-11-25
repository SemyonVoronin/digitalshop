package school.voronin.digitalshop.service;

import org.springframework.stereotype.Service;
import school.voronin.digitalshop.entity.BidEntity;
import school.voronin.digitalshop.entity.LotEntity;
import school.voronin.digitalshop.model.Lot;
import school.voronin.digitalshop.model.LotStatus;
import school.voronin.digitalshop.repository.BidRepository;
import school.voronin.digitalshop.repository.LotRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;
    private final BidRepository bidRepository;

    public LotServiceImpl(LotRepository lotRepository, BidRepository bidRepository) {
        this.lotRepository = lotRepository;
        this.bidRepository = bidRepository;
    }

    @Override
    public List<Lot> getAllLots() {
        return lotRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public List<Lot> getActiveLots() {
        return lotRepository.findByStatus(LotStatus.ACTIVE).stream().map(this::toDomain).toList();
    }

    @Override
    public Lot getLotById(UUID id) {
        LotEntity entity = lotRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Лот не найден: " + id));
        return toDomain(entity);
    }

    @Override
    public Lot createLot(Lot lot) {
        LotEntity entity = new LotEntity();
        entity.setTitle(lot.title());
        entity.setDescription(lot.description());
        entity.setStartPrice(lot.startPrice());
        entity.setCurrentPrice(lot.startPrice());
        entity.setSellerId(lot.sellerId());
        entity.setStatus(LotStatus.ACTIVE);
        entity.setEndsAt(lot.endsAt());

        LotEntity saved = lotRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Lot closeLot(UUID id) {
        LotEntity entity = lotRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Лот не найден: " + id));

        if (entity.getStatus() != LotStatus.ACTIVE){
            throw new IllegalStateException("Лот не активен");
        }

        UUID winnerId = bidRepository.findTopByLotIdOrderByAmountDesc(id)
                .map(BidEntity::getBuyerId)
                .orElse(null);

        entity.setStatus(LotStatus.CLOSED);
        entity.setWinnerId(winnerId);
        LotEntity saved = lotRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public void cancelLot(UUID id) {
        LotEntity entity = lotRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Лот не найден: " + id));

        entity.setStatus(LotStatus.CANCELLED);
        lotRepository.save(entity);
    }

    private Lot toDomain(LotEntity entity) {
        return new Lot(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStartPrice(),
                entity.getCurrentPrice(),
                entity.getSellerId(),
                entity.getWinnerId(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getEndsAt()
        );
    }
}