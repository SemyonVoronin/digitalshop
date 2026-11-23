package school.voronin.digitalshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import school.voronin.digitalshop.repository.BidRepository;

@Component
public class DbCheck implements CommandLineRunner {

    private final BidRepository bidRepository;

    public DbCheck(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("==================================================");
        System.out.println("ВСЕГО СТАВОК В БАЗЕ: " + bidRepository.count());
        bidRepository.findAll().forEach(bid -> {
            System.out.println("Ставка: ID=" + bid.getId() +
                    ", Лот=" + bid.getLotId() +
                    ", Покупатель=" + bid.getBuyerId() +
                    ", Сумма=" + bid.getAmount());
        });
        System.out.println("==================================================");
    }
}