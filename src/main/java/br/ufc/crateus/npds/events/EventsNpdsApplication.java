package br.ufc.crateus.npds.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "model")
public class EventsNpdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsNpdsApplication.class, args);
	}

}
