package school.voronin.digitalshop.service;

import school.voronin.digitalshop.model.Lot;

import java.util.List;
import java.util.UUID;

public interface LotService {
    List<Lot> getAllLots();
    List<Lot> getActiveLots();
    Lot getLotById(UUID id);
    Lot createLot(Lot lot);
    Lot closeLot(UUID id);
    void cancelLot(UUID id);
}
