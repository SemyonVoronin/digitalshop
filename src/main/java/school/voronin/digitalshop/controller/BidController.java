package school.voronin.digitalshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import school.voronin.digitalshop.model.Bid;
import school.voronin.digitalshop.service.BidService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lots/{lotId}/bids")
public class BidController {

    private static final Logger log = LoggerFactory.getLogger(BidController.class);
    private final BidService bidService;

    public BidController(BidService bidService){
        this.bidService = bidService;
    }

    @GetMapping
    public List<Bid> getBids(@PathVariable UUID lotId){
        log.info("Начало getBids: lotId = {}", lotId);
        List<Bid> bids = bidService.getBidsByLotId(lotId);
        log.info("Конец bids: найдено {} bids", bids.size());
        return bids;
    }

    @GetMapping("/top")
    public Bid getTopBid(@PathVariable UUID lotId){
        log.info("Начало getTopBid: lotId = {}", lotId);
        Bid top = bidService.getTopBid(lotId);
        log.info("Конец getTopBid: top = {}", top);
        return top;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bid placeBid(@PathVariable UUID lotId, @RequestBody BidRequest request){
        log.info("Начало placeBid: lotId = {}, request = {}", lotId, request);
        Bid bid = bidService.placeBid(lotId, request.buyerId(), request.amount());
        log.info("Конец placeBid: bid = {}", bid);
        return bid;
    }
}
