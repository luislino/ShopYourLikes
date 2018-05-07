package com.sylconnexity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sylconnexity.spring18.dbschema.*;

@SpringBootApplication
public class Spring18Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring18Application.class, args);
	}

	@Bean
	public CommandLineRunner generateMockData(ClickRepository clickRepo, LinkRepository linkRepo,
											  MerchantRepository merchantRepo, PublisherRepository publisherRepo) {
		return (args) -> {

		};
	}
}
