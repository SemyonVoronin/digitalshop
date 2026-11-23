package school.voronin.digitalshop.service;

import school.voronin.digitalshop.model.Lot;

import java.util.List;

public interface LotService {
    List<Lot> getAllLots();
    List<Lot> getActiveLots();
    Lot getLotById(Long id);
    Lot createLot(Lot lot);
    Lot closeLot(Long id);
    void cancelLot(Long id);
}
