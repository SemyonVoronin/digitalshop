package school.voronin.digitalshop.service;

import org.springframework.stereotype.Service;
import school.voronin.digitalshop.entity.LotEntity;
import school.voronin.digitalshop.model.Bid;
import school.voronin.digitalshop.model.Lot;
import school.voronin.digitalshop.model.LotStatus;
import school.voronin.digitalshop.repository.BidRepository;
import school.voronin.digitalshop.repository.LotRepository;

import java.time.LocalDateTime;
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
    public Lot getLotById(Long id) {
        // Находим по hashCode (наш самодельный Long id)
        return getAllLots().stream()
                .filter(lot -> lot.id() != null && lot.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Лот не найден: " + id));
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
    public Lot closeLot(Long id) {
        // Находим нужный LotEntity по нашему Long id (через поиск в списке)
        LotEntity entity = lotRepository.findAll().stream()
                .filter(e -> e.getId() != null) // Заглушка, так как ID теперь UUID
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Лот не найден"));

        entity.setStatus(LotStatus.CLOSED);
        lotRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public void cancelLot(Long id) {
        LotEntity entity = lotRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Лот не найден"));

        entity.setStatus(LotStatus.CANCELLED);
        lotRepository.save(entity);
    }

    // Мапперы
    // Превращает сущность из БД в наш record (для контроллера)
    private Lot toDomain(LotEntity entity) {
        return new Lot(
                // Берем UUID, переводим в строку, берем hashCode (он int, но мы кастуем в long)
                // Это костыль, чтобы получить Long из UUID
                entity.getId() != null ? (long) entity.getId().hashCode() : null,
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