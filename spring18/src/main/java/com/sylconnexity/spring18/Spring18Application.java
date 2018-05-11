package com.sylconnexity.spring18;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sylconnexity.spring18.dbschema.*;
import com.sylconnexity.spring18.util.ClickGenerator;
import com.sylconnexity.spring18.util.LinkGenerator;
import com.sylconnexity.spring18.util.MerchantGenerator;
import com.sylconnexity.spring18.util.PublisherGenerator;

/**
 * The entrypoint of the application.
 */
@SpringBootApplication
public class Spring18Application {

	/**
	 * Runs the application.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Spring18Application.class, args);
	}

	/**
	 * Runs the generation of mock data.
	 * @param clickRepo A click repository
	 * @param linkRepo A link repository
	 * @param merchantRepo A merchant repository
	 * @param publisherRepo A publisher repository
	 * @return A function that generates mock data if the publisher database is empty.
	 */
	@Bean
	public CommandLineRunner generateMockDataRunner(ClickRepository clickRepo, LinkRepository linkRepo,
											  MerchantRepository merchantRepo, PublisherRepository publisherRepo) {
		return (args) -> {
			if (publisherRepo.count() == 0)
				generateMockData(clickRepo, linkRepo, merchantRepo, publisherRepo);
		};
	}

	private void generateMockData(ClickRepository clickRepo, LinkRepository linkRepo,
							  MerchantRepository merchantRepo, PublisherRepository publisherRepo) {
		PublisherGenerator publisherGenerator = new PublisherGenerator(publisherRepo);
		MerchantGenerator merchantGenerator = new MerchantGenerator(merchantRepo);
		LinkGenerator linkGenerator = new LinkGenerator(linkRepo);
		ClickGenerator clickGenerator = new ClickGenerator(clickRepo);

		System.out.println("Starting Generation of Mock Data");
		for (int x = 1; x < 101; x++)
			publisherGenerator.generatePublisher();
		System.out.println("Publishers Generated");

		for (int x = 1; x < 101; x++)
			merchantGenerator.generateMerchant();
		System.out.println("Merchants Generated");

		for (int x = 1; x < 100; x++)
			linkGenerator.generateLink((long) 1, (long) x);
		System.out.println("Links Generated");

		for (int x = 1; x < 101; x++) {
			for (int y = 1; y < 101; y++)
				clickGenerator.generateClick((long) x);
		}
		System.out.println("Clicks Generated");
		System.out.println("Done Generating Mock Data");
	}
}
