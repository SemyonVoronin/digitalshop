package school.voronin.digitalshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DigitalshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalshopApplication.class, args);
	}

}
