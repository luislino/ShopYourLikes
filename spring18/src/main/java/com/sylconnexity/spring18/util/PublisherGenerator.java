package com.sylconnexity.spring18.util;

import com.sylconnexity.spring18.dbschema.Publisher;
import com.sylconnexity.spring18.dbschema.PublisherRepository;

/**
 * A RandomGenerator that generates random Publishers.
 */
public class PublisherGenerator extends RandomGenerator {
    private PublisherRepository publisherRepo;

    /**
     * Constructs a PublisherGenerator with a given repository.
     * @param publisherRepo A publisher repository
     */
    public PublisherGenerator(PublisherRepository publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    /**
     * Generates a random Publisher with a username of 20 characters and an API Key of 19 characters.
     */
    public void generatePublisher() {
        String username = generateRandomString(20);
        String apiKey = generateRandomString(19);
        publisherRepo.save(new Publisher(username, apiKey));
    }
}
