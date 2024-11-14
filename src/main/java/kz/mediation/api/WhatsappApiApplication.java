package kz.mediation.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WhatsappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsappApiApplication.class, args);
	}
}
