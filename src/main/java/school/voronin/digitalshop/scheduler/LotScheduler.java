package school.voronin.digitalshop.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import school.voronin.digitalshop.model.Lot;
import school.voronin.digitalshop.service.LotService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LotScheduler {

    private static final Logger log = LoggerFactory.getLogger(LotScheduler.class);
    private final LotService lotService;

    public LotScheduler(LotService lotService){
        this.lotService = lotService;
    }

    @Scheduled(fixedRate = 5000)
    public void closeExpiredLots(){
        log.info("Scheduled: проверяем истёкшие по времени лоты...");

        List<Lot> activeLots = lotService.getActiveLots();
        LocalDateTime now = LocalDateTime.now();

        for (Lot lot : activeLots){
            if (lot.endsAt().isBefore(now)){
                log.info("Закрываем истёкший лот id = {}, title = {}", lot.id(), lot.title());
                lotService.closeLot(lot.id());
            }
        }
    }
}