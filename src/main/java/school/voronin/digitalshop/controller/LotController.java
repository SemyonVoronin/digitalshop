package school.voronin.digitalshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import school.voronin.digitalshop.model.Lot;
import school.voronin.digitalshop.service.LotService;

import java.util.List;

@RestController
@RequestMapping("/api/lots")
public class LotController {

    private final LotService lotService;
    private static final Logger log = LoggerFactory.getLogger(LotController.class);

    public LotController(LotService lotService){
        this.lotService = lotService;
    }

    @GetMapping
    public List<Lot> getAllLots(){
        log.info("Начало getAllLots");
        List<Lot> lots = lotService.getAllLots();
        log.info("Конец getAllLots, найдено: {} лотов ", lots.size());
        return lots;
    }

    @GetMapping("/active")
    public List<Lot> getActiveLots(){
        log.info("Начало getActiveLots");
        List<Lot> lots = lotService.getActiveLots();
        log.info("Конец getActiveLots: найдено активных {} лотов", lots.size());
        return lots;
    }

    @GetMapping("/{id}")
    public Lot getLotById(@PathVariable Long id){
        log.info("Начало getLotById: id = {}", id);
        Lot lot = lotService.getLotById(id);
        log.info("Конец getLotById: lot = {}", lot);
        return lot;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lot createLot(@RequestBody Lot lot){
        log.info("Начало createLot");
        Lot created = lotService.createLot(lot);
        log.info("Конец createLot: lot = {}", created);
        return created;
    }

    @PostMapping("/{id}/close")
    public Lot closeLot(@PathVariable Long id){
        log.info("Начало closeLot: id = {}", id);
        Lot closed = lotService.closeLot(id);
        log.info("Конец closeLot: закрытый лот = {}", closed);
        return closed;
    }

    @PostMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelLot(@PathVariable Long id){
        log.info("Начало cancelLot: id = {}", id);
        lotService.cancelLot(id);
        log.info("Конец closeLot: отменённый лот = {}", id);
    }

}
